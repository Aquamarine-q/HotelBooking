package com.example.hotel.di.module

import com.example.hotel.data.api.HotelApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class HotelApiModule {

    @Provides
    fun provideHotelApi(retrofit: Retrofit): HotelApi {
        return retrofit.create(HotelApi::class.java)
    }
}