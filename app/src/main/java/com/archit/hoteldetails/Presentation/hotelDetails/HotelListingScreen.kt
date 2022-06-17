package com.archit.hoteldetails.Presentation.hotelDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.archit.hoteldetails.utils.createNotificationChannel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun HotelListingScreen(
    navigator: DestinationsNavigator,
    viewModel: HotelListingViewModel = hiltViewModel(),
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )
    val state = viewModel.state
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = {
                    viewModel.onEvent(
                        HotelListingEvent.OnSearchQueryChange(it)
                    )
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                placeholder =
                {
                    Text(text = "Search...")
                },
                maxLines = 1,
                singleLine = true
            )
            Button(onClick = { viewModel.logout() }) {
                Text(text = "LogOut")
            }
        }
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(HotelListingEvent.Refresh)
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.hotels.size) { i ->
                    val hotel = state.hotels[i]
                    HotelInfoCard(
                        name = hotel.name ,
                        city = hotel.city,
                        img = hotel.img!!,
                        stars =hotel.stars ,
                        rating =hotel.rating,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                navigator.navigate(
                                    ReviewInfoScreenDestination(hotel.id)
                                )
                            }
                    )
                    if(i < state.hotels.size) {
                        Divider(modifier = Modifier.padding(
                            horizontal = 16.dp
                        ))
                    }
                }
            }
        }
    }
}