package vub.sa.assignment

import akka.actor._
import scala.concurrent.duration._

case class PhotoMessage(licensePlate : String, speed : Int)

class CameraActor(nextFilter: ActorRef) extends Actor {
  override def receive: Receive = {
    case PhotoMessage(licence: String, speed: Int) =>
      //send message every 30 seconds
      println(licence)
      println(speed)
      nextFilter ! PhotoMessage(licence, speed)
    case _ =>
      println("error") // ERROR
      context.stop(self)
  }
}

class LicenseFilterActor(nextFilter: ActorRef) extends Actor {
  override def receive: Receive = {
    case PhotoMessage("", speed: Int) =>
      // We have to halt here (exception or halt ?)
      context.stop(self)

    case PhotoMessage(null, speed: Int) =>
      // We have to halt here (exception or halt ?)
      context.stop(self)

    case PhotoMessage(licence: String, speed: Int) =>
      // If we arrive here we have a possible infraction because the car
      // license exist so we have to continue to the next filter.
      nextFilter ! (licence, speed)
  }
}


class SpeedFilterActor(nextFilter: ActorRef) extends Actor {
  val errormsg: String = "Invalid message, dropped..."

  override def receive: Receive = {
    case PhotoMessage(licence: String, speed: Int) => if (speed < 70) {
      // If the speed is below the limit we have to halt or throw exception
      context.stop(self)
    }
    else {
      // We proceed incase it's an infraction case.
      nextFilter ! PhotoMessage(licence, speed)
    }
  }
}

class CPOfficeActor extends Actor {
  override def receive: Receive = {
    case PhotoMessage(licence: String, speed: Int) =>
      // If we get till here we have an infraction
      // println("Infraction by car with plate " + licence + ", for speeding at " + speed + " km/h has been processed")
      // sender() ! "Proecessed Infraction"
      context.stop(self)
  }
}


object assignment extends App {
  import scala.concurrent.ExecutionContext.Implicits.global

  val system = ActorSystem("Assignment4")
  val cpoffice = system.actorOf(Props[CPOfficeActor], "cpoffice")
  val licensefilter = system.actorOf(Props(classOf[LicenseFilterActor], cpoffice), "licensefilter")
  val speedfilter = system.actorOf(Props(classOf[SpeedFilterActor], licensefilter), "speedfilter")
  val camera = system.actorOf(Props(classOf[CameraActor], speedfilter), "camera")

  system.scheduler.schedule(
    0 seconds,
    30 seconds,
    camera,
    ("12345", 57)
  )
}
