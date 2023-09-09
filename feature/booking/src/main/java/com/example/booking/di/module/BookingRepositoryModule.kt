package com.example.booking.di.module

import com.example.booking.data.api.BookingApi
import com.example.booking.data.repository.BookingRepository
import dagger.Module
import dagger.Provides

@Module
class BookingRepositoryModule {

    @Provides
    fun provideBookingRepository(api: BookingApi) = BookingRepository(api)
}