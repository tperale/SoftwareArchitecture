package vub.sa.assignment

import akka.actor._
import scala.concurrent.duration._

case class PhotoMessage(licensePlate : String, speed : Int)

class CameraActor(nextFilter: ActorRef) extends Actor {
  override def receive: Receive = {
    case PhotoMessage(license: String, speed: Int) =>
      //send message every 30 seconds
      nextFilter ! PhotoMessage(license, speed)
    case _ =>
      println("[error] In the message structure sended") // ERROR
  }
}

class LicenseFilterActor(nextFilter: ActorRef) extends Actor {
  override def receive: Receive = {
    case PhotoMessage("", speed: Int) =>
      // We have to halt here (exception or halt ?)
      println("[error] No plate found by the camera")

    case PhotoMessage(null, speed: Int) =>
      // We have to halt here (exception or halt ?)
      println("[error] No plate found by the camera")

    case PhotoMessage(license: String, speed: Int) =>
      // If we arrive here we have a possible infraction because the car
      // license exist so we have to continue to the next filter.
      nextFilter ! PhotoMessage(license, speed)
  }
}


class SpeedFilterActor(nextFilter: ActorRef) extends Actor {
  val errormsg: String = "Invalid message, dropped..."

  override def receive: Receive = {
    case PhotoMessage(license: String, speed: Int) => if (speed < 70) {
      // If the speed is below the limit we have to halt or throw exception
      println(s"[info] vehicule is running below the speed limit (here ${speed} km/h), disgarding")
    }
    else {
      // We proceed incase it's an infraction case.
      nextFilter ! PhotoMessage(license, speed)
    }
  }
}

class CPOfficeActor extends Actor {
  override def receive: Receive = {
    case PhotoMessage(license: String, speed: Int) =>
      // If we get till here we have an infraction
      println(s"[info] Infraction detected for car plate ${license} running at ${speed} km/h")
  }
}

object PlateGenerator {
  val random = new scala.util.Random

  val MIN_SPEED = 55
  val MAX_SPEED = 95

  val PLATE_LENGTH = 6

  def generateSpeed: Int = {
    return MIN_SPEED + random.nextInt(MAX_SPEED - MIN_SPEED + 1)
  }

  def generatePlate: String = {
    return {random.alphanumeric take PLATE_LENGTH mkString}
  }

  def generate: PhotoMessage = {
    return new PhotoMessage(generatePlate, generateSpeed)
  }
}

object assignment extends App {
  import scala.concurrent.ExecutionContext.Implicits.global

  val system = ActorSystem("Assignment4")
  val cpoffice = system.actorOf(Props[CPOfficeActor], "cpoffice")
  val licensefilter = system.actorOf(Props(classOf[LicenseFilterActor], cpoffice), "licensefilter")
  val speedfilter = system.actorOf(Props(classOf[SpeedFilterActor], licensefilter), "speedfilter")
  val camera = system.actorOf(Props(classOf[CameraActor], speedfilter), "camera")

  system.scheduler.schedule(0 second, 30 second) {
     camera ! PlateGenerator.generate
  }
}
