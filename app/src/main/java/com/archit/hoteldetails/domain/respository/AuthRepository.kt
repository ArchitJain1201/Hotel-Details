package com.archit.hoteldetails.domain.respository

import com.archit.hoteldetails.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun isUserAuthenticatedInFirebase(): Boolean

    suspend fun firebaseSignIn(email: String, password: String): Flow<Resource<Boolean>>

//    suspend fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit)

    suspend fun signOut(): Flow<Resource<Boolean>>

    fun getFirebaseAuthState(): Flow<Boolean>
}