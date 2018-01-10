// package wwaldron.eggs.infrastructure
//
// import wwaldron.eggs.api.ApiModule
// import wwaldron.eggs.domain.{CookRepository, EggRepository, DomainModule}
//
// import scala.concurrent.ExecutionContext
//
// trait InfrastructureModule { this: ApiModule with DomainModule =>
//   // override val eggRepository: EggRepository = new InMemoryEggRepository()
//   //
//   // override val cookRepository: CookRepository = new InMemoryCookRepository(eggRepository)
//
//   override implicit def executionContext: ExecutionContext = scala.concurrent.ExecutionContext.global
// }


package wwaldron.eggs.infrastructure

import wwaldron.eggs.api.FoodPrepApi
import wwaldron.eggs.domain.{CookRepository, CookRepositoryFactory, EggRepository, DomainModule}

import com.google.inject.{Module, Binder}
import com.google.inject.assistedinject._

import scala.concurrent.ExecutionContext

class InfrastructureModule extends Module {
  def configure(binder: Binder) = {
    binder.bind(classOf[EggRepository]).to(classOf[InMemoryEggRepository])
    // binder.bind(classOf[CookRepository]).to(classOf[InMemoryCookRepository])
    binder.install(new FactoryModuleBuilder()
      .implement(classOf[CookRepository], classOf[InMemoryCookRepository])
      .build(classOf[CookRepositoryFactory]))
    binder.bind(classOf[ExecutionContext]).toInstance(ExecutionContext.global)
    // binder.bind(classOf[FoodPrepApi]).to(classOf[FoodPrepApi])
  }
}
