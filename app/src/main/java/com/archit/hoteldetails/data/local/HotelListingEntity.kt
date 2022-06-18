package com.archit.hoteldetails.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.json.Json

@Entity
data class HotelListingEntity(
    @PrimaryKey val idNo: Int? = null,
    val id: String?,
    val name: String?,
    val city: String?,
    val img: List<String>?,
    val stars: Int?,
    val rating: Float?,
)

class Converters {
    var gson = Gson()
        @TypeConverter
        fun stringToList(data: String?): List<String> {
//            if (data == null) {
//                return Collections.emptyList()
//            }
            val listType = object : TypeToken<List<String?>?>() {}.getType()
            return gson.fromJson<List<String>>(data, listType)
        }

        @TypeConverter
        fun ListToString(someObjects: List<String?>?): String {
            return gson.toJson(someObjects)
        }
}
