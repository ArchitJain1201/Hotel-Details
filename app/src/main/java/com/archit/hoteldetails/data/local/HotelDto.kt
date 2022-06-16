package com.archit.hoteldetails.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HotelDto {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotelListing(
        hotelListingEntites: List<HotelListingEntity>
    )

    @Query("DELETE FROM hotellisitngentity")
    suspend fun clearHotelListing()

    @Query(
        """
            SELECT *
            FROM hotellisitngentity
            WHERE LOWER(name) LIKE :query
        """
    )
    suspend fun searchHotelListing(query: String): List<HotelListingEntity>
}
