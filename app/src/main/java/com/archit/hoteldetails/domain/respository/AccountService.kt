package com.archit.hoteldetails.domain.respository

interface AccountService {
    fun hasUser(): Boolean
    fun getUserId(): String
    fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun signOut()
}