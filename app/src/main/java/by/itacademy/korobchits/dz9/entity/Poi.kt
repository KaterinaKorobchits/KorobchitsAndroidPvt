package by.itacademy.korobchits.dz9.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val POI_TABLE_NAME = "Poi"

@Entity(tableName = POI_TABLE_NAME)
data class Poi(
    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @Embedded
    @SerializedName("coordinate")
    val coordinate: Coordinate?,

    @SerializedName("fleetType")
    val fleetType: FleetType?,

    @SerializedName("heading")
    val heading: Double?
)