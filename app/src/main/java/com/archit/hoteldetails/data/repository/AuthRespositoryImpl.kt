package com.archit.hoteldetails.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.archit.hoteldetails.domain.respository.AuthRepository
import com.archit.hoteldetails.utils.Resource
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
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
//                    .addOnCompleteListener(this) { task ->
//                        if (task.isSuccessful) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success")
//                            val user = auth.currentUser
////                            updateUI(user)
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.exception)
//                            Toast.makeText(baseContext, "Authentication failed.",
//                                Toast.LENGTH_SHORT).show()
////                            updateUI(null)
//                        }
                emit(Resource.Success(true))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(e.message!! ?: "Not Authorized"))
                emit(Resource.Loading(false))
            }
        }
    }

//    override suspend fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit) {
//        Firebase.auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener { onResult(it.exception) }
//    }

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

    override fun getFirebaseAuthState(): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}