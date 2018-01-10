package wwaldron.eggs.infrastructure

import org.scalatest.FreeSpec
import org.scalatest.concurrent.ScalaFutures
import wwaldron.eggs.domain.DomainHelpers
import org.scalatest.time.SpanSugar._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import wwaldron.eggs.api.ApiModule

class InMemoryCookRepositoryTest extends FreeSpec with ScalaFutures with DomainHelpers {
  "findCook" - {
    "should eventually return a cook" in {
      val a: ApiModule = Injector.injector.getInstance(classOf[ApiModule])
      a.cookRepository.findOne().isReadyWithin(500 millis)
    }
  }
}
