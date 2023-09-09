package com.example.hotelbooking.app

import android.app.Application
import com.example.core.di.App
import com.example.core.di.provider.ApplicationProvider
import com.example.hotelbooking.di.ApplicationComponent

class HotelBookingApplication : Application(), App {

    private lateinit var appComponent: ApplicationProvider

    override fun onCreate() {
        super.onCreate()
        appComponent = ApplicationComponent.init()
    }

    override fun getApplicationProvider(): ApplicationProvider {
        return appComponent
    }
}