package com.archit.hoteldetails.data.repository

import com.archit.hoteldetails.data.local.HotelDatabase
import com.archit.hoteldetails.data.mapper.toHotelListing
import com.archit.hoteldetails.data.mapper.toHotelListingEntity
import com.archit.hoteldetails.data.mapper.toReviewInfo
import com.archit.hoteldetails.data.remote.HotelApi
import com.archit.hoteldetails.domain.model.HotelListing
import com.archit.hoteldetails.domain.model.ReviewInfo
import com.archit.hoteldetails.domain.respository.HotelRepository
import com.archit.hoteldetails.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HotelRepositoryImpl @Inject constructor(
    private val api: HotelApi,
    private val db: HotelDatabase
): HotelRepository {

    private val dao = db.dao

    override suspend fun getHotelListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<HotelListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchHotelListing(query)
            emit(Resource.Success(
                data = localListings.map { it.toHotelListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                api.getHotelListing()
                // if a API call is made it tell the compiler to parse it to a usable form
            } catch(e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                dao.clearHotelListing()
                dao.insertHotelListing(
                    listings.map { it .toHotelListingEntity()}
                )
                // here the data is cached
                emit(Resource.Success(
                    data = dao
                        .searchHotelListing("")
                        .map { it.toHotelListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getReviewInfo(id: String): Flow<Resource<List<ReviewInfo>>> {
        return flow {
            emit(Resource.Loading(true))
            val remoteListings = try {
                api.getReviewInfo(id)
                // if a API call is made it tell the compiler to parse it to a usable form
            } catch(e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                emit(Resource.Success(
                    data = listings.map { it.toReviewInfo() }
                ))
                emit(Resource.Loading(false))
            }
        }

    }

}