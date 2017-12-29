package wwaldron.eggs.api

import org.scalatest.FreeSpec
import org.scalatest.concurrent.ScalaFutures
import wwaldron.eggs.TestModule
import wwaldron.eggs.domain.{DomainHelpers, EggStyle, BaconStyle, WaffleStyle}

class FoodPrepApiTest
  extends FreeSpec
  with ScalaFutures
  with DomainHelpers {

  "prepareEgg" - {
    "should return a CookedEgg with the specified style" in new TestModule {
      foodRepository.add(createRawEgg())

      val expectedEgg = createCookedEgg(EggStyle.SunnySideUp)

      whenReady(foodPrepApi.prepare(EggStyle.SunnySideUp)) { egg =>
        assert(egg == expectedEgg)
      }
    }
  }

  "prepareBacon" - {
    "should return a CookedBacon with the specified style" in new TestModule {
      foodRepository.add(createRawBacon())

      val expectedBacon = createCookedBacon(BaconStyle.American)

      whenReady(foodPrepApi.prepare(BaconStyle.American)) { bacon =>
        assert(bacon == expectedBacon)
      }
    }
  }

  "prepareWaffle" - {
    "should return a CookedWaffle with the specified style" in new TestModule {
      foodRepository.add(createRawWaffle())

      val expectedWaffle = createCookedWaffle(WaffleStyle.Brussels)

      whenReady(foodPrepApi.prepare(WaffleStyle.Brussels)) { waffle =>
        assert(waffle == expectedWaffle)
      }
    }
  }

  // "prepareMixed" - {
  //   "should return a CookedBacon with the specified style" in new TestModule {
  //     foodRepository.add(createRawBacon())
  //     foodRepository.add(createRawWaffle())
  //
  //     val expectedBacon = createCookedBacon(BaconStyle.American)
  //
  //     whenReady(foodPrepApi.prepare(BaconStyle.American)) { bacon =>
  //       assert(bacon == expectedBacon)
  //     }
  //
  //   }
  // }
}
