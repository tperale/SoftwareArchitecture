package wwaldron.eggs.domain

import wwaldron.eggs.domain.Food.RawFood

case class Order(food: RawFood, style: FoodStyle)
