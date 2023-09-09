package com.example.booking.di.module

import com.example.booking.data.api.BookingApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class BookingApiModule {

    @Provides
    fun provideBookingApi(retrofit: Retrofit): BookingApi {
        return retrofit.create(BookingApi::class.java)
    }
}