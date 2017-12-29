package wwaldron.eggs.domain

import scala.concurrent.duration._

sealed trait WaffleStyle extends FoodStyle

object WaffleStyle {
  case object Brussels extends WaffleStyle
  case object Liege extends WaffleStyle
}
