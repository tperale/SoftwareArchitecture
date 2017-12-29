package wwaldron.eggs.infrastructure

import wwaldron.eggs.domain.Egg.RawEgg
import wwaldron.eggs.domain.EggRepository

import scala.collection.mutable
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

class InMemoryEggRepository(implicit ec: ExecutionContext) extends EggRepository {
  private val eggs = mutable.Queue[RawEgg]()

  override def findAndRemove(): Future[Option[RawEgg]] = Future {
    eggs.synchronized {
      Try(eggs.dequeue()).toOption
    }
  }

  override def add(egg: RawEgg): Future[Unit] = Future {
    eggs.synchronized {
      eggs.enqueue(egg)
    }
  }
}
