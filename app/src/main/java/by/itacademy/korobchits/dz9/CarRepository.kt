package by.itacademy.korobchits.dz9

import by.itacademy.classwork.cw9.entity.CoordParams
import by.itacademy.classwork.cw9.entity.Poi

interface CarRepository {
    fun getCarByCoord(params: CoordParams, listener: CarRepositoryResult)
}

interface CarRepositoryResult {
    fun onDataReady(data: List<Poi>)
    fun onError(throwable: Throwable)
}
