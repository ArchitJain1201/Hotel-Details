package com.archit.hoteldetails.di

import android.app.Application
import androidx.room.Room
import com.archit.hoteldetails.data.local.HotelDatabase
import com.archit.hoteldetails.data.remote.HotelApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHotelApi(): HotelApi {
        return Retrofit.Builder()
            .baseUrl(HotelApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }).build())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideHotelDatabase(app: Application): HotelDatabase {
        return Room.databaseBuilder(
            app,
            HotelDatabase::class.java,
            "hoteldb.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }
}