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
      val expectedEgg = createCookedEgg(EggStyle.SunnySideUp)
      orderRepository.add(createOrder(createRawEgg(), EggStyle.SunnySideUp))

      whenReady(foodPrepApi.prepare()) { egg =>
        assert(egg == expectedEgg)
      }
    }
  }

  "prepareBacon" - {
    "should return a CookedBacon with the specified style" in new TestModule {
      val expectedBacon = createCookedBacon(BaconStyle.American)
      orderRepository.add(createOrder(createRawBacon(), BaconStyle.American))

      whenReady(foodPrepApi.prepare()) { bacon =>
        assert(bacon == expectedBacon)
      }
    }
  }

  "prepareWaffle" - {
    "should return a CookedWaffle with the specified style" in new TestModule {
      val expectedWaffle = createCookedWaffle(WaffleStyle.Brussels)
      orderRepository.add(createOrder(createRawWaffle(), WaffleStyle.Brussels))

      whenReady(foodPrepApi.prepare()) { waffle =>
        assert(waffle == expectedWaffle)
      }
    }
  }

  "prepareMixed" - {
    "should return a CookedBacon with the specified style" in new TestModule {
      orderRepository.add(createOrder(createRawBacon(), BaconStyle.American))
      orderRepository.add(createOrder(createRawWaffle(), WaffleStyle.Brussels))
      orderRepository.add(createOrder(createRawBacon(), BaconStyle.American))

      val expectedBacon = createCookedBacon(BaconStyle.American)
      val expectedWaffle = createCookedWaffle(WaffleStyle.Brussels)

      whenReady(foodPrepApi.prepare()) { item =>
        assert(item == expectedBacon)
      }

      whenReady(foodPrepApi.prepare()) { item =>
        assert(item == expectedWaffle)
      }

      whenReady(foodPrepApi.prepare()) { item =>
        assert(item == expectedBacon)
      }
    }
  }
}
