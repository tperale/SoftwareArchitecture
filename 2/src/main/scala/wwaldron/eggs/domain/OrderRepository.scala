package wwaldron.eggs.domain

import scala.concurrent.Future

trait OrderRepository {
  def findAndRemove(): Future[Option[Order]]
  def remaining(): Int
  def add(food: Order): Future[Unit]
}
