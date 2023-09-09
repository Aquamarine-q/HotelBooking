package com.example.hotel.presentation.viewstate

data class HotelViewState(
    val hotelPhoto: List<String>,
    val rating: String,
    val ratingName: String,
    val name: String,
    val address: String,
    val minimalPrice: String,
    val priceForIt: String,
    val peculiarities: List<String>,
    val description: String,
)