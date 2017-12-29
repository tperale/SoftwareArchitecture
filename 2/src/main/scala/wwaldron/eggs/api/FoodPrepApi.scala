package wwaldron.eggs.api

import wwaldron.eggs.domain.Food.CookedFood
import wwaldron.eggs.domain.{CookRepository, FoodStyle}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success
import scala.util.Failure

class FoodPrepApi(cookRepository: CookRepository)(implicit ec: ExecutionContext) {
   def prepare(style: FoodStyle): Future[CookedFood] = {
    for {
      cook <- cookRepository.findOne()
      food <- cook.prepare(style)
    } yield food
  }

  def prepare2(style: FoodStyle): Future[CookedFood] = {
   val cookFuture = cookRepository.findOne();
   cookFuture.onComplete {
       case Success(cook) =>
         return cook.prepare(style)
       case Failure(e) =>
         return Future.failed(e)
       }
   return Future.failed(new RuntimeException("Should not happen"))
   }


 def prepare3(style: FoodStyle): Future[CookedFood] = {
   val cookFuture = cookRepository.findOne()
   //using flatMap instead of map to avoid returning a future of a future
   cookFuture.flatMap(c => c.prepare(style))
  }

}
