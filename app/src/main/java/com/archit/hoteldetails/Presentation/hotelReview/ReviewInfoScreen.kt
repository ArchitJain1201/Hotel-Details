package com.archit.hoteldetails.Presentation.hotelReview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.archit.hoteldetails.ui.theme.DarkBlue
import com.archit.hoteldetails.utils.createNotificationChannel
import com.archit.hoteldetails.utils.simpleNotification
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun ReviewInfoScreen(
    navigator: DestinationsNavigator,
    id: String,
    viewModel: ReviewInfoViewModel = hiltViewModel()
) {
    val channelId = "Review"
    val notificationId = 0
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        createNotificationChannel(channelId, context)
    }

    val state = viewModel.state
    if(state.error == null) {
        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = {
                           Text(text = "Book Hotel")
                    },
                    onClick = {
                        simpleNotification(
                            context,
                            channelId,
                            notificationId,
                            "Simple Notification",
                            "This is a simple notification with default priority."
                        )
                    })
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBlue)
                    .padding(16.dp)
            ) {
                items(state.reviewInfo!!.size){ i->
                    val reviewInfo = state.reviewInfo[i]
                    Text(
                        text = reviewInfo.name!!,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = reviewInfo.comment!!,
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Center
    ) {
        if(state.isLoading) {
            CircularProgressIndicator()
        } else if(state.error != null) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error
            )
        }
    }
}