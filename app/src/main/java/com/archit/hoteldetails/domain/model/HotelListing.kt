package com.archit.hoteldetails.domain.model

data class HotelListing(
    val id: String?,
    val name: String?,
    val city: String?,
    val img: List<String>?,
    val stars: Int?,
    val rating: Float?,
)
