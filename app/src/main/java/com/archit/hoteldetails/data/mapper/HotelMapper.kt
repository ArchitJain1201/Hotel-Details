package com.archit.hoteldetails.data.mapper

import com.archit.hoteldetails.data.local.HotelListingEntity
import com.archit.hoteldetails.data.remote.dto.HotelListingDto
import com.archit.hoteldetails.data.remote.dto.ReviewInfoDto
import com.archit.hoteldetails.domain.model.HotelListing
import com.archit.hoteldetails.domain.model.ReviewInfo


fun HotelListingEntity.toHotelListing(): HotelListing {
    return HotelListing(
        id = id,
        name = name,
        city = city,
        img = img,
        stars = stars,
        rating = rating,
    )
}

fun HotelListingDto.toHotelListingEntity(): HotelListingEntity {
    return HotelListingEntity(
        id = id,
        name = name,
        city = city,
        img = img,
        stars = stars,
        rating = rating,
    )
}

fun ReviewInfoDto.toReviewInfo(): ReviewInfo {
    return ReviewInfo(
        name = name ?: "",
        comment = comment?:""
    )
}

