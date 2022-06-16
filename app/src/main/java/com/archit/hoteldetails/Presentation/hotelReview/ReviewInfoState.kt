package com.archit.hoteldetails.Presentation.hotelReview

import com.archit.hoteldetails.domain.model.ReviewInfo

data class ReviewInfoState(
    val reviewInfo: ReviewInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
