package wwaldron.eggs.infrastructure

import wwaldron.eggs.domain.{Order, OrderRepository}

import scala.collection.mutable
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

class InMemoryOrderRepository(implicit ec: ExecutionContext) extends OrderRepository {
  private val orders = mutable.Queue[Order]()

  override def findAndRemove(): Future[Option[Order]] = Future {
    orders.synchronized {
      Try(orders.dequeue()).toOption
    }
  }

  override def remaining(): Int = orders.length

  override def add(order: Order): Future[Unit] = Future {
    orders.synchronized {
      orders.enqueue(order)
    }
  }
}
