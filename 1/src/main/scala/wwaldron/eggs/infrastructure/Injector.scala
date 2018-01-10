package wwaldron.eggs.infrastructure

import wwaldron.eggs.api.ApiModule

import com.google.inject.{Guice, Module}

trait Injector {
  Injector.inject(this)
}

object Injector {
  val injector = Guice.createInjector(new InfrastructureModule())

  def inject(obj: AnyRef) {
    injector.injectMembers(obj)
  }

}
