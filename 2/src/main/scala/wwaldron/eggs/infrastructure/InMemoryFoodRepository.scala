package wwaldron.eggs.infrastructure

import wwaldron.eggs.domain.Food.RawFood
import wwaldron.eggs.domain.FoodRepository

import scala.collection.mutable
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

class InMemoryFoodRepository(implicit ec: ExecutionContext) extends FoodRepository {
  private val foods = mutable.Queue[RawFood]()

  override def findAndRemove(): Future[Option[RawFood]] = Future {
    foods.synchronized {
      Try(foods.dequeue()).toOption
    }
  }

  override def add(food: RawFood): Future[Unit] = Future {
    foods.synchronized {
      foods.enqueue(food)
    }
  }
}
