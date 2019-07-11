package by.itacademy.classwork.cw9.entity

import com.google.gson.annotations.SerializedName

data class Coordinate(
    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("longitude")
    val longitude: Double
)
