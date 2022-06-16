package com.archit.hoteldetails.Presentation.hotelReview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.archit.hoteldetails.domain.respository.HotelRepository
import com.archit.hoteldetails.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: HotelRepository
): ViewModel() {

    var state by mutableStateOf(ReviewInfoState())

    init {
        viewModelScope.launch {
            val id = savedStateHandle.get<String>("id") ?: return@launch
            state = state.copy(isLoading = true)
            val reviewInfoResult = async { repository.getReviewInfo(id) }
            when(val result = reviewInfoResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        reviewInfo = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        reviewInfo = null
                    )
                }
                else -> Unit
            }
        }
    }
}