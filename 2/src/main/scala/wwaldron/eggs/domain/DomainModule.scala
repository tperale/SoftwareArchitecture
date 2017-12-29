package wwaldron.eggs.domain

trait DomainModule {
  def cookRepository: CookRepository
  def foodRepository: FoodRepository
}
