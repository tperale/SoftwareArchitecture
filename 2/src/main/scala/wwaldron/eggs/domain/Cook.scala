package wwaldron.eggs.domain

import wwaldron.eggs.domain.Food.CookedFood

import scala.concurrent.{ExecutionContext, Future}

case class CookId(value: Integer) extends AnyVal

object Cook {
  case object OutOfFoodsException extends IllegalStateException("There are no more foods")
}

class Cook(val id: CookId)(orderRepository: OrderRepository)(implicit ec: ExecutionContext) {
  import Cook._ //to get OutOfEggsException here
  private val fryingPan = new EmptyFryingPan()

  private def wait(pan: FullFryingPan) = {
    while(!pan.checkDoneness())
      Thread.sleep(10)
  }

  // Here is our accept() method for the client in the Visitor design patttern
  // The Visitor are the foods.
  // Cook is the visitable
  def prepare(): Future[CookedFood] = {
    println("hallo")
    orderRepository.findAndRemove().map { orderOption =>
      val order = orderOption.getOrElse(throw OutOfFoodsException)
      val fullFryingPan = fryingPan.add(order.food, order.style)
      wait(fullFryingPan)
      fullFryingPan.remove()._2
    }
  }
}
