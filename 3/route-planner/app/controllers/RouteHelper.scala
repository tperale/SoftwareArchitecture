package controllers

import play.api.Environment
import scala.io

object RouteHelper {
  var stationsFile = Environment.simple().getFile("/public/data/stations.csv")
  var stationsList: List[String] = io.Source.fromFile(stationsFile).getLines.toList


  def isStation(station: String) = {
    stationsList.exists((x: String) => { x == station })
  }

  def filter(term: String) = {
    stationsList.filter(_.startsWith(term))
  }
}
