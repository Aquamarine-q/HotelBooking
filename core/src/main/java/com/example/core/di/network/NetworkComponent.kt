package com.example.core.di.network

import com.example.core.di.provider.NetworkProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
    ]
)
interface NetworkComponent: NetworkProvider {

    companion object {

        fun create(): NetworkProvider {
            return DaggerNetworkComponent.builder().build()
        }
    }
}