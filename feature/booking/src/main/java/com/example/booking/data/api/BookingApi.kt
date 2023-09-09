package com.example.booking.data.api

import com.example.booking.data.model.Booking
import retrofit2.http.GET

interface BookingApi {

    @GET("e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun getBooking(): Booking
}