package controllers

import javax.inject._

import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json._
import play.api.libs.ws._
import scala.concurrent.Future
import scala.util.Try
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io
import javax.inject.Inject

import play.api.mvc._
import RouteForm._
import model.JsonModel._
import scala.collection.mutable._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class RoutePlanner  @Inject()(cc: MessagesControllerComponents, ws: WSClient) extends MessagesAbstractController(cc)  {

  val baseRequest: WSRequest = ws.url("https://api.irail.be/connections")
  var stationsFile = Environment.simple().getFile("/public/data/stations.csv")
  var stationsList = List[String]()
  private val connections = ArrayBuffer[Connection]()

  // URL to the method to calculate the connections.  You can call this directly from the template, but it
  // can be more convenient to leave the template completely stateless
  private val postURL = routes.RoutePlanner.calculateConnections()

  def index() = Action { implicit request: MessagesRequest[AnyContent] =>
    stationsList = io.Source.fromFile(stationsFile).getLines.toList
    Ok(views.html.index(form, connections, postURL))
  }

  // This will be the action that handles our ajax request
  def stations(term: String) = Action { implicit request: Request[AnyContent] =>
    val suggestions = stationsList.filter(_.startsWith(term))
    Ok(Json.toJson(suggestions))

  }

  def calculateConnections() = Action.async { implicit request: MessagesRequest[AnyContent] =>
    connections.clear()

    form.bindFromRequest.fold(
        errors => {
            Logger.error("ERRORS: " + errors);

            val futureOk = Future {  }
            futureOk.map(i => Ok(views.html.index(form, connections, postURL)))
        },
        connectionData => {
            val url = s"https://api.irail.be/connections/?from=${connectionData.from}&to=${connectionData.to}&format=json&lang=en&fast=false&typeOfTransport=trains&alerts=false&results=6"
            val futureResponse = ws.url(url).addHttpHeaders("Accept" -> "application/json").get()

            futureResponse.map { response =>
                val result: JsValue = response.json
                val c = (result \ "connection").get.as[Seq[Connection]]

                connections.appendAll(c)

                Ok(views.html.index(form, connections, postURL))
            }

        }
    )
  }

}
