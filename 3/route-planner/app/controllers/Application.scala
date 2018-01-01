package controllers

import javax.inject.Inject
import play.api.mvc._
import play.api.routing._

class Application @Inject()(components: ControllerComponents) extends AbstractController(components) {

  // Read more about JavaScript Routers here:1  https://www.playframework.com/documentation/2.6.x/ScalaJavascriptRouting
  def javascriptRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.RoutePlanner.stations,
      )
    ).as("text/javascript")
  }

}
