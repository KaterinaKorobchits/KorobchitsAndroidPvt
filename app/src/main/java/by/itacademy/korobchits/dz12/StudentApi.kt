package by.itacademy.korobchits.dz12

import by.itacademy.korobchits.dz6.Dz6Student
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface StudentApi {

    @GET("data/student")
    fun getStudents(
        @Query("pageSize") pageSize: Int,
        @Query("offset") offset: Int
    ): Observable<List<Dz6Student>>

    @GET("data/student")
    fun getStudentsByFilterName(
        @Query("pageSize") pageSize: Int,
        @Query("offset") offset: Int,
        @Query("where") condition: String
    ): Observable<List<Dz6Student>>

    @GET("data/student/{id}")
    fun getStudentById(
        @Path("id") id: String
    ): Observable<Dz6Student>

    @DELETE("data/student/{id}")
    fun delete(
        @Path("id") id: String
    ): Completable

    @PUT("data/student/{id}")
    fun update(
        @Path("id") id: String,
        @Body student: Dz6Student
    ): Completable

    @POST("data/student")
    fun insert(
        @Body student: Dz6Student
    ): Completable
}