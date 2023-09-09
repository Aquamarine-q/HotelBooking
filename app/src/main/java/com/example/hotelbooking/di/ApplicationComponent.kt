package com.example.hotelbooking.di

import com.example.core.di.provider.ApplicationProvider
import com.example.core.di.provider.NetworkProvider
import com.example.core.di.network.NetworkComponent
import com.example.core.di.viewmodel.ViewModelFactoryModule
import com.example.hotelbooking.app.HotelBookingApplication
import dagger.Component

@Component(
    dependencies = [
        NetworkProvider::class
    ],
    modules = [
        ViewModelFactoryModule::class
    ]
)
interface ApplicationComponent : ApplicationProvider {

    companion object {

        fun init(): ApplicationProvider {
            val networkProvider = NetworkComponent.create()
            return DaggerApplicationComponent.factory().create(networkProvider)
        }
    }

    @Component.Factory
    interface Factory {

        fun create(networkProvider: NetworkProvider): ApplicationComponent
    }

    fun inject(application: HotelBookingApplication)
}