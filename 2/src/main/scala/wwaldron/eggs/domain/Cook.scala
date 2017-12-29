package wwaldron.eggs.domain

import wwaldron.eggs.domain.Food.CookedFood

import scala.concurrent.{ExecutionContext, Future}

case class CookId(value: Integer) extends AnyVal

object Cook {
  case object OutOfEggsException extends IllegalStateException("There are no more eggs")
}

class Cook(val id: CookId)(foodRepository: EggRepository)(implicit ec: ExecutionContext) {
  import Cook._ //to get OutOfEggsException here
  private val fryingPan = new EmptyFryingPan()

  private def wait(pan: FullFryingPan) = {
    while(!pan.checkDoneness())
      Thread.sleep(10)
  }

  // Here is our accept() method for the client in the Visitor design patttern
  // The Visitor are the foods.
  // Cook is the visitable
  def prepare(style: FoodStyle): Future[CookedFood] = {
    println("hallo")
    foodRepository.findAndRemove().map { eggOption =>
      val food = eggOption.getOrElse(throw OutOfEggsException)
      val fullFryingPan = fryingPan.add(food, style)
      wait(fullFryingPan)
      fullFryingPan.remove()._2
    }
  }
}
