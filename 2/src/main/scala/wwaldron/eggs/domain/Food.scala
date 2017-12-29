package wwaldron.eggs.domain

trait Food

object Food extends Food {
  trait CookedFood {
    def style: FoodStyle
  }
  trait RawFood {
    def startCooking(style: FoodStyle): PartiallyCookedFood
  }
  trait PartiallyCookedFood extends CookedFood {
    def isDone: Boolean
    def finishCooking(): CookedFood
  }
  trait FullyCookedFood extends CookedFood
}
