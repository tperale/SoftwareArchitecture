package controllers

import javax.inject._

import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json._
import play.api.libs.ws._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io
import javax.inject.Inject

import play.api.mvc._
import RouteForm._
import model.JsonModel._
import scala.collection.mutable._

case class ConnectionData(From: String, To: String)

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


  private val connectionForm = Form(
    mapping(
      "From" -> nonEmptyText,
      "To" -> nonEmptyText,
    )(ConnectionData.apply)(ConnectionData.unapply)
  )

  def index() = Action { implicit request: MessagesRequest[AnyContent] =>
    stationsList = io.Source.fromFile(stationsFile).getLines.toList
    Ok(views.html.index(form, connections, postURL))
  }

  // This will be the action that handles our ajax request
  def stations(term: String) = Action { implicit request: Request[AnyContent] =>
    val suggestions = stationsList.filter(_.startsWith(term))
    Ok(Json.toJson(suggestions))

  }

  // TODO: This will be the action that handles our form post
  def calculateConnections() = Action { implicit request: MessagesRequest[AnyContent] =>
    connectionForm.bindFromRequest.fold(
        errors => {
            Logger.error("ERRORS: " + errors);

        },
        connectionData => {
            Logger.info("From is: " + connectionData.From);
            Logger.info("To is: " + connectionData.To);
        }
    )
    Ok(views.html.index(form, connections, postURL))
  }

}
