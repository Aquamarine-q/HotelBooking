package com.example.hotel.di

import com.example.core.di.provider.ApplicationProvider
import com.example.hotel.di.module.HotelApiModule
import com.example.hotel.di.module.HotelRepositoryModule
import com.example.hotel.di.module.HotelViewModelsModule
import com.example.hotel.presentation.fragment.HotelFragment
import dagger.Component

@Component(
    dependencies = [
        ApplicationProvider::class,
    ],
    modules = [
        HotelViewModelsModule::class,
        HotelApiModule::class,
        HotelRepositoryModule::class,
    ]
)
interface HotelComponent {
    companion object {

        fun init(applicationProvider: ApplicationProvider): HotelComponent {
            return DaggerHotelComponent.factory().create(applicationProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(applicationProvider: ApplicationProvider): HotelComponent
    }

    fun inject(fragment: HotelFragment)
}