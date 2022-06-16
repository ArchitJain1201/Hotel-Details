package com.archit.hoteldetails.Presentation.hotelDetails

sealed class HotelListingEvent {
    object Refresh: HotelListingEvent()
    data class OnSearchQueryChange(val query: String): HotelListingEvent()
}
