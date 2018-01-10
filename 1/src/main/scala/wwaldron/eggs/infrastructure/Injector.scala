package wwaldron.eggs.infrastructure

import wwaldron.eggs.api.ApiModule
import wwaldron.eggs.domain.DomainModule

import com.google.inject.{Guice, Module}

trait Injector {
  Injector.inject(this)
}

object Injector {
  val injector = Guice.createInjector(new InfrastructureModule())

  def inject(obj: AnyRef) {
    injector.injectMembers(obj)
  }

  // def getInstance[T](obj: Class[T]) {
  //   injector.getInstance(obj)
  // }
}

// class Injector extends DomainModule with ApiModule with InfrastructureModule
