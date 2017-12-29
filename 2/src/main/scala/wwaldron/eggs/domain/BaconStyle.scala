package wwaldron.eggs.domain

import scala.concurrent.duration._

sealed trait BaconStyle extends FoodStyle

object BaconStyle {
  case object American extends BaconStyle
  case object Candied extends BaconStyle
  case object Applewood extends BaconStyle
}
