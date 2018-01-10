package wwaldron.eggs.domain

import com.google.inject.Inject

class DomainModule {
  @Inject val cookRepository: CookRepository = null
  @Inject val eggRepository: EggRepository = null
}
