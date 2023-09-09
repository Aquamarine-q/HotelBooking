package com.example.hotel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.data.model.Hotel
import com.example.hotel.data.repository.HotelRepository
import com.example.hotel.presentation.viewstate.HotelViewState
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class HotelViewModel
@Inject constructor(private val repository: HotelRepository) : ViewModel() {

    private val _viewState = MutableLiveData(getDefaultState())
    val viewState: LiveData<HotelViewState> = _viewState

    fun onScreenOpened() {
        viewModelScope.launch {
            try {
                updateViewState(repository.getHotel())
            } catch (exception: IOException) {

            }
        }
    }

    private fun updateViewState(hotel: Hotel) {
        with(hotel) {
            _viewState.postValue(
                _viewState.value?.copy(
                    hotelPhoto = image_urls,
                    rating = rating.toString(),
                    ratingName = rating_name,
                    name = name,
                    address = adress,
                    minimalPrice = minimal_price.toString(),
                    priceForIt = price_for_it,
                    peculiarities = about_the_hotel.peculiarities,
                    description = about_the_hotel.description,
                )
            )
        }
    }

    private fun getDefaultState(): HotelViewState {
        return HotelViewState(
            hotelPhoto = listOf(),
            rating = "0",
            ratingName = "Ok",
            name = "Hotel",
            address = "hotel's address",
            minimalPrice = "000000",
            priceForIt = " ",
            peculiarities = listOf("1", "2", "3", "4"),
            description = "hotel's description"
        )
    }
}