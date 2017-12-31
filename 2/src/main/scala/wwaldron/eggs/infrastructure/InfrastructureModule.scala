package wwaldron.eggs.infrastructure

import wwaldron.eggs.api.ApiModule
import wwaldron.eggs.domain.{CookRepository, OrderRepository, DomainModule}

import scala.concurrent.ExecutionContext

trait InfrastructureModule { this: ApiModule with DomainModule =>
  override val orderRepository: OrderRepository = new InMemoryOrderRepository()

  override val cookRepository: CookRepository = new InMemoryCookRepository(orderRepository)

  override implicit def executionContext: ExecutionContext = scala.concurrent.ExecutionContext.global
}
