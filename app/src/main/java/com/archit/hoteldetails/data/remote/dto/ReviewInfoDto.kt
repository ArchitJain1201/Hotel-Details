package com.archit.hoteldetails.data.remote.dto

import com.squareup.moshi.Json

data class ReviewInfoDto(
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "comment") val comment: String?
)
