package by.itacademy.korobchits.dz15

import androidx.room.TypeConverter
import by.itacademy.korobchits.dz9.entity.FleetType

class Converters {

    companion object {
        @TypeConverter
        @JvmStatic
        fun getFromFleetTypeDb(value: FleetType): String {
            return value.name
        }

        @TypeConverter
        @JvmStatic
        fun toFleetTypeDb(value: String): FleetType {
            return FleetType.valueOf(value)
        }
    }
}