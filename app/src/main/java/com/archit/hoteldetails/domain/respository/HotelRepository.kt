package com.archit.hoteldetails.domain.respository

import com.archit.hoteldetails.domain.model.HotelListing
import com.archit.hoteldetails.domain.model.ReviewInfo
import com.archit.hoteldetails.utils.Resource
import kotlinx.coroutines.flow.Flow

interface HotelRepository {

    suspend fun getHotelListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<HotelListing>>>

    suspend fun getReviewInfo(
        symbol: String
    ): Flow<Resource<List<ReviewInfo>>>
}