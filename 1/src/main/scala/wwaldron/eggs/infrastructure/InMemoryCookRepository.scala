package wwaldron.eggs.infrastructure

import wwaldron.eggs.domain.{CookId, EggRepository, Cook, CookRepository}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random
import com.google.inject.Inject
import com.google.inject.assistedinject.Assisted

class InMemoryCookRepository @Inject()(
  @Assisted eggRepository: EggRepository
)(implicit ec: ExecutionContext) extends CookRepository {
  override def findOne(): Future[Cook] = {
    Future.successful(new Cook(CookId(Random.nextInt()))(eggRepository))
  }
}
