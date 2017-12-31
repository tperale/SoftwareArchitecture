package wwaldron.eggs.infrastructure

import wwaldron.eggs.domain.{CookId, OrderRepository, Cook, CookRepository}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

class InMemoryCookRepository(orderRepository: OrderRepository)(implicit ec: ExecutionContext) extends CookRepository {
  override def findOne(): Future[Cook] = {
    Future.successful(new Cook(CookId(Random.nextInt()))(orderRepository))
  }
}
