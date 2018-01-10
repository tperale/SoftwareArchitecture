package wwaldron.eggs.api

import org.scalatest.FreeSpec
import org.scalatest.concurrent.ScalaFutures
import wwaldron.eggs.TestModule
import wwaldron.eggs.domain.{DomainHelpers, EggStyle}
import wwaldron.eggs.infrastructure.Injector

class FoodPrepApiTest
  extends FreeSpec
  with ScalaFutures
  with DomainHelpers {

  "prepareEgg" - {
    "should return a CookedEgg with the specified style" in {
      val a: ApiModule = Injector.injector.getInstance(classOf[ApiModule])
      a.eggRepository.add(createRawEgg())

      // println(eggRepository)
      // println(cookRepository)

      val expectedEgg = createCookedEgg(EggStyle.SunnySideUp)

      whenReady(a.foodPrepApi.prepareEgg(EggStyle.SunnySideUp)) { egg =>
        assert(egg == expectedEgg)
      }
    }
  }
}
