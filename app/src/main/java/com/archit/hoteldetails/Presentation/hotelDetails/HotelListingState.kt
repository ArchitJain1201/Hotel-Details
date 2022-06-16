package com.archit.hoteldetails.Presentation.hotelDetails

import com.archit.hoteldetails.domain.model.HotelListing

data class HotelListingState(
    val companies: List<HotelListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
