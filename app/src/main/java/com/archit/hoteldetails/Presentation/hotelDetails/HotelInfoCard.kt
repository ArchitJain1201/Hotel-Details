package com.archit.hoteldetails.Presentation.hotelDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.archit.hoteldetails.utils.createNotificationChannel
import com.archit.hoteldetails.utils.simpleNotification

@Composable
fun HotelInfoCard(
    name: String?,
    city: String?,
    img: List<String>,
    stars: Int?,
    rating: Float?,
    modifier: Modifier = Modifier
){
    val channelId = "Review"
    val notificationId = 0
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        createNotificationChannel(channelId, context)
    }
    val painter =
        rememberImagePainter(data = img[1])
    Card(
        elevation = 10.dp,
        modifier = modifier
    ) {
        Image(
            painter = painter,
            contentDescription = "Forest Image",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = name!!)
            Text(text = city!!)

            Row() {
                Text(text = stars.toString())
                    Icon(
                        painter = painterResource(id = androidx.appcompat.R.drawable.abc_ic_star_black_16dp),
                        contentDescription = null,
                        tint = Color.Yellow// decorative element
                    )
            }
            Text(text = rating!!.toString().take(4))
        }
        Button(onClick = {
            simpleNotification(
                context,
                channelId,
                notificationId,
                "Simple Notification",
                "This is a simple notification with default priority."
            )
        }) {
            Text(text = "Buy Tickets")
        }
    }
}