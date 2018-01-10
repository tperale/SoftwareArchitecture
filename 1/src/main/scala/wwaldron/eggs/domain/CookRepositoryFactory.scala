package wwaldron.eggs.domain

// import wwaldron.eggs.domain.{CookRepository, EggRepository}

trait CookRepositoryFactory {
  def create(eggRepository: EggRepository): CookRepository
}
