package com.archit.hoteldetails.Presentation.auth

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.archit.hoteldetails.Presentation.destinations.HotelListingScreenDestination
import com.archit.hoteldetails.utils.Resource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun AuthScreen(
    navigator: DestinationsNavigator,
    authViewModel: AuthViewModel = hiltViewModel()
    ) {
    LaunchedEffect(Unit) {
        if(authViewModel.isUserAuthenticated) {
            navigator.navigate(
                HotelListingScreenDestination()
            )
        }
    }

    if (!authViewModel.isUserAuthenticated) {
        Scaffold(
            topBar = {
                TopAppBar (
                    title = {
                        Text(
                            text = "AUTH_SCREEN"
                        )
                    }
                )
            }
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    var TextFieldEmailState = remember{mutableStateOf("")}

                    TextField(
                        value = TextFieldEmailState.value,
                        onValueChange = {
                                newInput -> TextFieldEmailState.value = newInput
                        },
                        label = {
                            Text(
                                text = "E-mail address",
                                color = MaterialTheme.colors.onPrimary
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .background(color = MaterialTheme.colors.onPrimary)
                    )

                    var TextFieldPasswordState = remember{mutableStateOf("")}

                    TextField(
                        value = TextFieldPasswordState.value,
                        onValueChange = { newInput -> TextFieldPasswordState.value = newInput },
                        label = {Text(text = "Password",color = MaterialTheme.colors.onPrimary)},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .background(color = MaterialTheme.colors.onPrimary)
                    )
                    Button(
                        onClick = {
                                  authViewModel.signIn(TextFieldEmailState.toString(),TextFieldPasswordState.toString())
                        },
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .requiredWidth(277.dp)
                    ){
                        Text(text = "Sign In")
                    }
                }
            }
            when(val response = authViewModel.signInState.value) {
                is Resource.Loading -> Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
                is Resource.Success -> {
                    if (response.data!!) {
                        navigator.navigate(
                            HotelListingScreenDestination()
                        )
                    }
                }
                is Resource.Error -> Log.d(TAG, response.message!!)
            }
        }
    }
}