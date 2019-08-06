package by.itacademy.korobchits.dz9.entity

import com.google.gson.annotations.SerializedName

data class Poi(
    @SerializedName("id")
    val id: String,

    @SerializedName("coordinate")
    val coordinate: Coordinate?,

    @SerializedName("fleetType")
    val fleetType: FleetType?,

    @SerializedName("heading")
    val heading: Double?
)