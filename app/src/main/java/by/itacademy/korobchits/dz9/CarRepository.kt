package by.itacademy.korobchits.dz9

import by.itacademy.korobchits.dz9.entity.CarResponse
import by.itacademy.korobchits.dz9.entity.CoordParams
import by.itacademy.korobchits.dz9.entity.Poi
import io.reactivex.Single

interface CarRepository {
    fun getCarByCoord(params: CoordParams, listener: CarRepositoryResult)
    fun getCars(params: CoordParams): Single<CarResponse>
}

interface CarRepositoryResult {
    fun onDataReady(data: List<Poi>)
    fun onError(throwable: Throwable)
}
