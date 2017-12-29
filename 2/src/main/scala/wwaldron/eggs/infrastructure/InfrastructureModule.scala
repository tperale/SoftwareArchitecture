package wwaldron.eggs.infrastructure

import wwaldron.eggs.api.ApiModule
import wwaldron.eggs.domain.{CookRepository, FoodRepository, DomainModule}

import scala.concurrent.ExecutionContext

trait InfrastructureModule { this: ApiModule with DomainModule =>
  override val foodRepository: FoodRepository = new InMemoryFoodRepository()

  override val cookRepository: CookRepository = new InMemoryCookRepository(foodRepository)

  override implicit def executionContext: ExecutionContext = scala.concurrent.ExecutionContext.global
}
