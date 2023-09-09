package com.example.booking.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking.data.model.Booking
import com.example.booking.data.repository.BookingRepository
import com.example.booking.presentation.viewstate.BookingViewState
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class BookingViewModel
@Inject constructor(private val repository: BookingRepository) : ViewModel() {

    private val _viewState = MutableLiveData(getDefaultState())
    val viewState: LiveData<BookingViewState> = _viewState

    fun onScreenOpened() {
        viewModelScope.launch {
            try {
                updateViewState(repository.getBooking())
                Log.i("test", repository.getBooking().toString())
            } catch (exception: IOException) {

            }
        }
    }

    private fun updateViewState(booking: Booking) {
        with(booking) {
            _viewState.postValue(
                _viewState.value?.copy(
                    hotelName = hotel_name,
                    hotelAddress = hotel_adress,
                    horating = horating.toString(),
                    ratingName = rating_name,
                    departure = departure,
                    arrivalCountry = arrival_country,
                    tourDateStart = tour_date_start,
                    tourDateStop = tour_date_stop,
                    nightsCount = number_of_nights.toString(),
                    room = room,
                    nutrition = nutrition,
                    tourPrice = tour_price,
                    fuelCharge = fuel_charge,
                    serviceCharge = service_charge,
                    toPay = tour_price + fuel_charge + service_charge,
                )
            )
        }
    }

    private fun getDefaultState(): BookingViewState {
        return BookingViewState(
            hotelName = "Hotel",
            hotelAddress = "Address",
            horating = "0",
            ratingName = "Ok",
            departure = "Here",
            arrivalCountry = "There",
            tourDateStart = "Today",
            tourDateStop = "Today",
            nightsCount = "0",
            room = "Room",
            nutrition = "Nutrition",
            tourPrice = 100000,
            fuelCharge = 100000,
            serviceCharge = 100000,
            toPay = 100000,
        )
    }
}