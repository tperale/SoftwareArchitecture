package wwaldron.eggs.domain

import wwaldron.eggs.domain.Food.{CookedFood, RawFood, PartiallyCookedFood, FullyCookedFood}

sealed trait Egg

object Egg {
  sealed trait CookedEgg extends CookedFood {
    def style: EggStyle
  }

  case class RawEgg() extends RawFood {
    def startCooking(style: FoodStyle): PartiallyCookedEgg =
      PartiallyCookedEgg(style)
  }

  case class PartiallyCookedEgg(style: FoodStyle) extends PartiallyCookedFood {
    private val startTime: Timestamp = Timestamp()
    def isDone: Boolean =
      (System.currentTimeMillis() - startTime.value) > style.cookTime.toMillis

    def finishCooking(): CookedFood =
      if(isDone) FullyCookedEgg(style) else this
  }

  case class FullyCookedEgg(style: FoodStyle) extends FullyCookedFood
}
