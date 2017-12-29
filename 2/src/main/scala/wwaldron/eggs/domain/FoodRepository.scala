package wwaldron.eggs.domain

import wwaldron.eggs.domain.Food.RawFood

import scala.concurrent.Future

trait FoodRepository {
  def findAndRemove(): Future[Option[RawFood]]
  def add(food: RawFood): Future[Unit]
}
