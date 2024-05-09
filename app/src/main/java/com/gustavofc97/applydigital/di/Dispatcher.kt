package com.gustavofc97.applydigital.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: ApplyDispatchers)

enum class ApplyDispatchers {
    Default,
    IO,
}
