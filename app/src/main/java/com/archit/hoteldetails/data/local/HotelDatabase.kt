package com.archit.hoteldetails.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities =[HotelListingEntity::class],
    version = 1
)
abstract class HotelDatabase: RoomDatabase() {
    abstract val dao: HotelDto
}