package wwaldron.eggs.infrastructure

import wwaldron.eggs.api.FoodPrepApi
import wwaldron.eggs.domain.{CookRepository, CookRepositoryFactory, EggRepository}

import com.google.inject.{Module, Binder}
import com.google.inject.assistedinject._

import scala.concurrent.ExecutionContext

class InfrastructureModule extends Module {
  def configure(binder: Binder) = {
    binder.bind(classOf[EggRepository]).to(classOf[InMemoryEggRepository])
    binder.install(new FactoryModuleBuilder()
      .implement(classOf[CookRepository], classOf[InMemoryCookRepository])
      .build(classOf[CookRepositoryFactory]))
    binder.bind(classOf[ExecutionContext]).toInstance(ExecutionContext.global)
  }
}
