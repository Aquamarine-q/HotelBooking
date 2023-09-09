package com.example.number.di

import com.example.core.di.provider.ApplicationProvider
import com.example.number.data.repository.NumberRepository
import com.example.number.di.module.NumberApiModule
import com.example.number.di.module.NumberViewModelsModule
import com.example.number.presentation.fragment.NumberFragment
import dagger.Component

@Component(
    dependencies = [
        ApplicationProvider::class,
    ],
    modules = [
        NumberViewModelsModule::class,
        NumberApiModule::class,
        NumberRepository::class,
    ]
)
interface NumberComponent {

    companion object {

        fun init(applicationProvider: ApplicationProvider): NumberComponent {
            return DaggerNumberComponent.factory().create(applicationProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(applicationProvider: ApplicationProvider): NumberComponent
    }

    fun inject(fragment: NumberFragment)
}