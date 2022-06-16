package com.archit.hoteldetails.Presentation.hotelDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.archit.hoteldetails.domain.respository.HotelRepository
import com.archit.hoteldetails.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelListingViewModel @Inject constructor(
    private val repository: HotelRepository
): ViewModel() {

    var state by mutableStateOf(HotelListingState())

    private var searchJob: Job? = null

    init {
        getHotelListing()
    }

    fun onEvent(event: HotelListingEvent) {
        when(event) {
            is HotelListingEvent.Refresh -> {
                getHotelListing(fetchFromRemote = true)
            }
            is HotelListingEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getHotelListing()
                }
            }
        }
    }

    private fun getHotelListing(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getHotelListings(fetchFromRemote, query)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    companies = listings
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}