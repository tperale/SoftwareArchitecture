package wwaldron.eggs.infrastructure

import wwaldron.eggs.domain.{CookId, EggRepository, Cook, CookRepository}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

import com.google.inject.Inject

class InMemoryCookRepository @Inject()(
  val eggRepository: EggRepository,
)(
  implicit ec: ExecutionContext
) extends CookRepository {

  override def findOne(): Future[Cook] = {
    Future.successful(new Cook(CookId(Random.nextInt()))(eggRepository))
  }
}
