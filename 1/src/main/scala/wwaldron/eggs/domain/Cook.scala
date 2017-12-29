package wwaldron.eggs.domain

import wwaldron.eggs.domain.Egg.CookedEgg

import scala.concurrent.{ExecutionContext, Future}

case class CookId(value: Integer) extends AnyVal

object Cook {
  case object OutOfEggsException extends IllegalStateException("There are no more eggs")
}

class Cook(val id: CookId)(eggRepository: EggRepository)(implicit ec: ExecutionContext) {
  import Cook._ //to get OutOfEggsException here
  private val fryingPan = new EmptyFryingPan()

  private def wait(pan: FullFryingPan) = {
    while(!pan.checkDoneness()) 
      Thread.sleep(10)
  }
   
  def prepareEgg(style: EggStyle): Future[CookedEgg] = {
    println("hallo")
    eggRepository.findAndRemove().map { eggOption =>
      val egg = eggOption.getOrElse(throw OutOfEggsException)
      val fullFryingPan = fryingPan.add(egg, style)
      wait(fullFryingPan)
      fullFryingPan.remove()._2
    }
  }
}
