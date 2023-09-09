package com.example.hotel.data.repository

import com.example.hotel.data.api.HotelApi
import com.example.hotel.data.model.Hotel
import javax.inject.Inject

class HotelRepository
@Inject constructor(private val api: HotelApi) {

    suspend fun getHotel(): Hotel {
        return api.getHotel()
    }
}