package com.example.booking.data.repository

import com.example.booking.data.api.BookingApi
import com.example.booking.data.model.Booking
import javax.inject.Inject

class BookingRepository
@Inject constructor(private val api: BookingApi) {

    suspend fun getBooking(): Booking {
        return api.getBooking()
    }
}