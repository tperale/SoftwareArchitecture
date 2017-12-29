package wwaldron.eggs.domain

import wwaldron.eggs.domain.Food.{CookedFood, RawFood, PartiallyCookedFood, FullyCookedFood}

sealed trait Bacon

object Bacon {
  sealed trait CookedBacon extends CookedFood {
    def style: BaconStyle
  }

  case class RawBacon() extends RawFood {
    def startCooking(style: FoodStyle): PartiallyCookedBacon =
      PartiallyCookedBacon(style)
  }

  case class PartiallyCookedBacon(style: FoodStyle) extends PartiallyCookedFood {
    private val startTime: Timestamp = Timestamp()
    def isDone: Boolean =
      (System.currentTimeMillis() - startTime.value) > style.cookTime.toMillis

    def finishCooking(): CookedFood =
      if(isDone) FullyCookedBacon(style) else this
  }

  case class FullyCookedBacon(style: FoodStyle) extends FullyCookedFood
}
