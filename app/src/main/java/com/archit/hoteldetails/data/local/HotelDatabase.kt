package com.archit.hoteldetails.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities =[HotelListingEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class HotelDatabase: RoomDatabase() {
    abstract val dao: HotelDto
}