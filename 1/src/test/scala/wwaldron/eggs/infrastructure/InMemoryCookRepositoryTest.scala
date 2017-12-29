package wwaldron.eggs.infrastructure

import org.scalatest.FreeSpec
import org.scalatest.concurrent.ScalaFutures
import wwaldron.eggs.domain.DomainHelpers
import org.scalatest.time.SpanSugar._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import wwaldron.eggs.domain.Cook

class InMemoryCookRepositoryTest extends FreeSpec with ScalaFutures with DomainHelpers {
  class TestContext {
    val eggRepo = new InMemoryEggRepository
    val cookRepo = new InMemoryCookRepository(eggRepo)
  }
  
  "findCook" - {
    "should eventually return a cook" in new TestContext {
      cookRepo.findOne().isReadyWithin(500 millis)
    }
  }
  
}