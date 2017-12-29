package wwaldron.eggs.infrastructure

import wwaldron.eggs.api.ApiModule
import wwaldron.eggs.domain.DomainModule
import wwaldron.eggs.infrastructure.{ServiceInjector}

trait Injector extends DomainModule with ApiModule with ServiceInjector
