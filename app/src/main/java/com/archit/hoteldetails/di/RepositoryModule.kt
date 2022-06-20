package com.archit.hoteldetails.di

import com.archit.hoteldetails.data.repository.AuthRespositoryImpl
import com.archit.hoteldetails.data.repository.HotelRepositoryImpl
import com.archit.hoteldetails.domain.respository.AuthRepository
import com.archit.hoteldetails.domain.respository.HotelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindHotelRepository(
        hotelRepositoryImpl: HotelRepositoryImpl
    ): HotelRepository

    @Binds
    @Singleton
    @ExperimentalCoroutinesApi
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRespositoryImpl
    ): AuthRepository
}
