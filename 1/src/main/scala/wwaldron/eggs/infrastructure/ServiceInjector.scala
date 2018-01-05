package wwaldron.eggs.infrastructure

import com.google.inject.{Guice, Module}

trait ServiceInjector {
  ServiceInjector.inject(this)
}

object ServiceInjector {
  private val injector = Guice.createInjector(new InfrastructureModule())
  def inject(obj: AnyRef) {
    println("hi there")
    injector.injectMembers(obj)
  }
}
