package wwaldron.eggs.domain

// import wwaldron.eggs.domain.Egg.{CookedEgg, PartiallyCookedEgg, RawEgg}
import wwaldron.eggs.domain.Food.{CookedFood, PartiallyCookedFood, RawFood}

trait FryingPan

case class EmptyFryingPan() extends FryingPan {
  def add(food: RawFood, style: FoodStyle): FullFryingPan = FullFryingPan(food.startCooking(style))
}

case class FullFryingPan(food: PartiallyCookedFood) extends FryingPan {
  def checkDoneness(): Boolean = food.isDone
  def remove(): (EmptyFryingPan, CookedFood) = (EmptyFryingPan(), food.finishCooking())
}
