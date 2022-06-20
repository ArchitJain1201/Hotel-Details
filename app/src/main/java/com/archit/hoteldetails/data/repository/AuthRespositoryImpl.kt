package com.archit.hoteldetails.data.repository

import com.archit.hoteldetails.domain.respository.AuthRepository
import com.archit.hoteldetails.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
@ExperimentalCoroutinesApi
class AuthRespositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
):AuthRepository {

    override fun isUserAuthenticatedInFirebase(): Boolean = auth.currentUser != null

    override suspend fun firebaseSignIn(email: String, password: String): Flow<Resource<Boolean>> {
        return flow {
            try {
                emit(Resource.Loading(true))
                Firebase.auth.signInWithEmailAndPassword(email, password)
                emit(Resource.Success(true))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(e.message!! ?: "Not Authorized"))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun signOut(): Flow<Resource<Boolean>> {
        return flow {
            try {
                emit(Resource.Loading(true))
                Firebase.auth.signOut()
                emit(Resource.Success(true))
                emit(Resource.Loading(false))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(e.message!!?:"Couldn't Signout"))
                emit(Resource.Loading(false))
            }
        }
    }
}