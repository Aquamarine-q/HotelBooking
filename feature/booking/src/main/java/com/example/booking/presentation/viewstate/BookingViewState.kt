package com.example.booking.presentation.viewstate

data class BookingViewState(
    val hotelName: String,
    val hotelAddress: String,
    val horating: String,
    val ratingName: String,
    val departure: String,
    val arrivalCountry: String,
    val tourDateStart: String,
    val tourDateStop: String,
    val nightsCount: String,
    val room: String,
    val nutrition: String,
    val tourPrice: Int,
    val fuelCharge: Int,
    val serviceCharge: Int,
    val toPay: Int
)