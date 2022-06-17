package com.archit.hoteldetails.Presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.archit.hoteldetails.domain.respository.AuthRepository
import com.archit.hoteldetails.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {
    val isUserAuthenticated get() = repository.isUserAuthenticatedInFirebase()

    private val _signInState = mutableStateOf<Resource<Boolean>>(Resource.Success(false))
    val signInState: State<Resource<Boolean>> = _signInState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            repository.firebaseSignIn(email,password).collect { response ->
                _signInState.value = response
            }
        }
    }
}