package wwaldron.eggs.domain

import wwaldron.eggs.domain.Food.{CookedFood, RawFood, PartiallyCookedFood, FullyCookedFood}

sealed trait Waffle

object Waffle {
  sealed trait CookedWaffle extends CookedFood {
    def style: WaffleStyle
  }

  case class RawWaffle() extends RawFood {
    def startCooking(style: FoodStyle): PartiallyCookedWaffle =
      PartiallyCookedWaffle(style)
  }

  case class PartiallyCookedWaffle(style: FoodStyle) extends PartiallyCookedFood {
    private val startTime: Timestamp = Timestamp()
    def isDone: Boolean =
      (System.currentTimeMillis() - startTime.value) > style.cookTime.toMillis

    def finishCooking(): CookedFood =
      if(isDone) FullyCookedWaffle(style) else this
  }

  case class FullyCookedWaffle(style: FoodStyle) extends FullyCookedFood
}
