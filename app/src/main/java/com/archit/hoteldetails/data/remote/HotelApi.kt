package com.archit.hoteldetails.data.remote

import com.archit.hoteldetails.data.remote.dto.HotelListingDto
import com.archit.hoteldetails.data.remote.dto.ReviewInfoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HotelApi {
    @GET("/hotels")
    suspend fun getHotelListing(): List<HotelListingDto>

    @GET("/reviews")
    suspend fun getReviewInfo(
        @Query("hotel_id") id: String,
    ): List<ReviewInfoDto>

    companion object {
        const val BASE_URL = "http://fake-hotel-api.herokuapp.com/api"
    }
}