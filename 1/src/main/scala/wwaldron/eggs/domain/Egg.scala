package wwaldron.eggs.domain

case class Timestamp(value: Long = System.currentTimeMillis())

sealed trait Egg

object Egg {

  sealed trait CookedEgg extends Egg {
    def style: EggStyle
  }
  
  case class RawEgg() extends Egg {
    def startCooking(style: EggStyle): PartiallyCookedEgg =
      PartiallyCookedEgg(style)
  }

  case class PartiallyCookedEgg(style: EggStyle) extends CookedEgg {
    private val startTime: Timestamp = Timestamp()
    def isDone: Boolean = 
      (System.currentTimeMillis() - startTime.value) > style.cookTime.toMillis

    def finishCooking(): CookedEgg = 
      if(isDone) FullyCookedEgg(style) else this
  }

  case class FullyCookedEgg(style: EggStyle) extends CookedEgg
}
