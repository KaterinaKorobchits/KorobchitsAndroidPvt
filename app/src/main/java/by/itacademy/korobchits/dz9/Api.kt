package by.itacademy.korobchits.dz9

import by.itacademy.classwork.cw9.entity.CarResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("t/fake-api/car-service.php")
    fun getCarsByCoord(
        @Query("p1Lat") p1Lat: Double,
        @Query("p1Lon") p1Lon: Double,
        @Query("p2Lat") p2Lat: Double,
        @Query("p2Lon") p2Lon: Double
    ): Call<CarResponse>

    @GET("t/fake-api/car-service.php?p1Lat=23423&p1Lon=342&p2Lat=3242&p2Lon=3453")
    fun getCarsByCoord1(): Call<CarResponse>
}