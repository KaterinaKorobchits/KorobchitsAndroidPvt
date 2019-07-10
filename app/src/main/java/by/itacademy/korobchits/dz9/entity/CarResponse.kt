package by.itacademy.classwork.cw9.entity

import com.google.gson.annotations.SerializedName

data class CarResponse(
    @SerializedName("poiList")
    val poiList: List<Poi>
)