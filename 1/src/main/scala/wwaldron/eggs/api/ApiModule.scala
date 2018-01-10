package wwaldron.eggs.api

import wwaldron.eggs.domain.{CookRepository, CookRepositoryFactory, EggRepository}
import wwaldron.eggs.infrastructure.Injector
import com.google.inject.Inject
import scala.concurrent.ExecutionContext

class ApiModule @Inject()(eggRepo: EggRepository, cookRepo: CookRepositoryFactory) {
  implicit def executionContext: ExecutionContext = ExecutionContext.global

  val eggRepository: EggRepository = eggRepo
  val cookRepository: CookRepository = cookRepo.create(eggRepo)

  // @Inject var cookRepository: CookRepository
  // @Inject var eggRepository: EggRepository

  val foodPrepApi: FoodPrepApi = new FoodPrepApi(cookRepository)
}
