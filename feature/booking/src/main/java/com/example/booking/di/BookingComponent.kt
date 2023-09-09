package com.example.booking.di

import com.example.booking.di.module.BookingApiModule
import com.example.booking.di.module.BookingRepositoryModule
import com.example.booking.di.module.BookingViewModelsModule
import com.example.booking.presentation.fragment.BookingFragment
import com.example.booking.presentation.fragment.PaidFragment
import com.example.core.di.provider.ApplicationProvider
import dagger.Component

@Component(
    dependencies = [
        ApplicationProvider::class,
    ],
    modules = [
        BookingViewModelsModule::class,
        BookingApiModule::class,
        BookingRepositoryModule::class,
    ]
)
interface BookingComponent {

    companion object {

        fun init(applicationProvider: ApplicationProvider): BookingComponent {
            return DaggerBookingComponent.factory().create(applicationProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(applicationProvider: ApplicationProvider): BookingComponent
    }

    fun inject(fragment: BookingFragment)
    fun inject(fragment: PaidFragment)
}