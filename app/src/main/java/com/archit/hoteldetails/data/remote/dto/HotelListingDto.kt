package com.archit.hoteldetails.data.remote.dto

import com.squareup.moshi.Json

data class HotelListingDto(
    @field:Json(name = "id") val id: String?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "city") val city: String?,
    @field:Json(name = "images") val img: List<String>?,
    @field:Json(name = "stars") val stars: String?,
    @field:Json(name = "rating") val rating: String?,
)
//TODO: Take Rating first 4 digit