package wwaldron.eggs

import wwaldron.eggs.api.ApiModule
import wwaldron.eggs.domain.DomainModule
import wwaldron.eggs.infrastructure.ServiceInjector

class TestModule extends DomainModule with ServiceInjector with ApiModule
