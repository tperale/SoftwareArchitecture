package wwaldron.eggs.api

import wwaldron.eggs.domain.Egg.CookedEgg
import wwaldron.eggs.domain.{CookRepository, EggStyle}

import com.google.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success
import scala.util.Failure

class FoodPrepApi (cookRepository: CookRepository)(implicit ec: ExecutionContext) {
  def prepareEgg(style: EggStyle): Future[CookedEgg] = {
    for {
      cook <- cookRepository.findOne()
      egg <- cook.prepareEgg(style)
    } yield egg
  }

  def prepareEgg2(style: EggStyle): Future[CookedEgg] = {
   val cookFuture = cookRepository.findOne();
   cookFuture.onComplete {
       case Success(cook) =>
         return cook.prepareEgg(style)
       case Failure(e) =>
         return Future.failed(e)
       }
   return Future.failed(new RuntimeException("Should not happen"))
   }


 def prepareEgg3(style: EggStyle): Future[CookedEgg] = {
   val cookFuture = cookRepository.findOne()
   //using flatMap instead of map to avoid returning a future of a future
   cookFuture.flatMap(c => c.prepareEgg(style))
  }

}
