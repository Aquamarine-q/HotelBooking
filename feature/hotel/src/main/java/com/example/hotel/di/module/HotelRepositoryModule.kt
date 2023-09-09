package com.example.hotel.di.module

import com.example.hotel.data.api.HotelApi
import com.example.hotel.data.repository.HotelRepository
import dagger.Module
import dagger.Provides

@Module
class HotelRepositoryModule {

    @Provides
    fun provideHotelRepository(api: HotelApi) = HotelRepository(api)
}