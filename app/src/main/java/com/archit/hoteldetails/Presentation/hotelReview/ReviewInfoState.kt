package com.archit.hoteldetails.Presentation.hotelReview

import com.archit.hoteldetails.domain.model.ReviewInfo

data class ReviewInfoState(
    val reviewInfo: List<ReviewInfo>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
