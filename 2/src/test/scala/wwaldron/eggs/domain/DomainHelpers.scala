package wwaldron.eggs.domain

import wwaldron.eggs.domain.Egg.RawEgg
import wwaldron.eggs.domain.Bacon.RawBacon
import wwaldron.eggs.domain.Waffle.RawWaffle

trait DomainHelpers {
  // EGG
  def createRawEgg() = RawEgg()

  def createPartiallyCookedEgg(style: EggStyle = createEggStyle()) = createRawEgg().startCooking(style)

  def createCookedEgg(style: EggStyle = createEggStyle()) = {
    val egg = createRawEgg().startCooking(style)
    Thread.sleep(style.cookTime.toMillis * 2)
    egg.finishCooking()
  }

  def createEggStyle(): EggStyle = EggStyle.HardBoiled

  // BACON
  def createRawBacon() = RawBacon()

  def createPartiallyCookedBacon(style: BaconStyle = createBaconStyle()) = createRawBacon().startCooking(style)

  def createCookedBacon(style: BaconStyle = createBaconStyle()) = {
    val bacon = createRawBacon().startCooking(style)
    Thread.sleep(style.cookTime.toMillis * 2)
    bacon.finishCooking()
  }

  def createBaconStyle(): BaconStyle = BaconStyle.American

  // WAFFLE
  def createRawWaffle() = RawWaffle()

  def createPartiallyCookedWaffle(style: WaffleStyle = createWaffleStyle()) = createRawWaffle().startCooking(style)

  def createCookedWaffle(style: WaffleStyle = createWaffleStyle()) = {
    val waffle = createRawWaffle().startCooking(style)
    Thread.sleep(style.cookTime.toMillis * 2)
    waffle.finishCooking()
  }

  def createWaffleStyle(): WaffleStyle = WaffleStyle.Brussels
}
