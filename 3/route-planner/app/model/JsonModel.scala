package model

object JsonModel {

  case class Connection(id: String, departure: Departure, arrival: Arrival, duration: String)
  case class Departure(station: String, time: String, vehicle: String, platform: String  )
  case class Arrival(station: String, time: String, vehicle: String, platform: String)

  // TODO: For the assignment you have to implement the Json Reads Combinators of the case classes
  // https://www.playframework.com/documentation/2.6.x/ScalaJsonCombinators


}


