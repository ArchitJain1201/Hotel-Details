package com.archit.hoteldetails.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class HotelListingEntity(
    @PrimaryKey val idNo: Int? = null,
    val id: String?,
    val name: String?,
    val city: String?,
    val img: List<String>?,
    val stars: String?,
    val rating: String?,
)
