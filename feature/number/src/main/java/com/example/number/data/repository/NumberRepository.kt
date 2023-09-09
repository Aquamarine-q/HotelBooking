package com.example.number.data.repository

import com.example.number.data.model.Rooms
import com.example.number.data.api.NumberApi
import dagger.Module
import javax.inject.Inject

@Module
class NumberRepository
@Inject constructor(private val api: NumberApi) {

    suspend fun getRooms(): Rooms {
        return api.getRooms()
    }
}