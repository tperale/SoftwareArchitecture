package model

import play.api.libs.json._

object JsonModel {

  case class Connection(id: String, departure: Departure, arrival: Arrival, duration: String)
  object Connection {
      implicit val readsConnection: Reads[Connection] = new Reads[Connection] {
          def reads(json: JsValue): JsResult[Connection] = {
              for {
                id <- (json \ "id").validate[String]
                departure <- (json \ "departure").validate[Departure]
                arrival <- (json \ "arrival").validate[Arrival]
                duration <- (json \ "duration").validate[String]
              } yield Connection(id, departure, arrival, duration)
          }
      }
  }
  case class Departure(station: String, time: String, vehicle: String, platform: String  )
  object Departure {
      implicit val readsDeparture: Reads[Departure] = new Reads[Departure] {
          def reads(json: JsValue): JsResult[Departure] = {
              for {
                station <- (json \ "station").validate[String]
                time <- (json \ "time").validate[String]
                vehicule <- (json \ "vehicle").validate[String]
                platform <- (json \ "platform").validate[String]
              } yield Departure(station, time, vehicule, platform)
          }
      }
  }
  case class Arrival(station: String, time: String, vehicle: String, platform: String)
  object Arrival {
      implicit val readsArrival: Reads[Arrival] = new Reads[Arrival] {
          def reads(json: JsValue): JsResult[Arrival] = {
              for {
                station <- (json \ "station").validate[String]
                time <- (json \ "time").validate[String]
                vehicule <- (json \ "vehicle").validate[String]
                platform <- (json \ "platform").validate[String]
              } yield Arrival(station, time, vehicule, platform)
          }
      }
  }

  // TODO: For the assignment you have to implement the Json Reads Combinators of the case classes
  // https://www.playframework.com/documentation/2.6.x/ScalaJsonCombinators


}
