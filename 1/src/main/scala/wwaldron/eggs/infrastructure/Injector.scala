package wwaldron.eggs.infrastructure

import wwaldron.eggs.api.ApiModule
import wwaldron.eggs.domain.DomainModule

trait Injector extends DomainModule with ApiModule with ServiceInjector
