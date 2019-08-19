package by.itacademy.korobchits.dz9

import by.itacademy.korobchits.dao.PoiDao
import by.itacademy.korobchits.dz9.entity.CarResponse
import by.itacademy.korobchits.dz9.entity.CoordParams
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarRepositoryRemote(private val api: Api, private val poiDao: PoiDao) : CarRepository {

    override fun getCars(params: CoordParams): Single<CarResponse> {
        return api
            .getCars(
                params.coord1.latitude,
                params.coord1.longitude,
                params.coord2.latitude,
                params.coord2.longitude
            )
            .doOnSuccess {
                poiDao.insert(it.poiList)
            }
            .onErrorResumeNext {
                var poiList = poiDao.get()
                if (poiList.isNotEmpty()) {
                    Single.just(CarResponse(poiList))
                } else
                    Single.error(it)
            }
    }

    override fun getCarByCoord(params: CoordParams, listener: CarRepositoryResult) {
        api
            .getCarsByCoord(
                params.coord1.latitude,
                params.coord1.longitude,
                params.coord2.latitude,
                params.coord2.longitude
            )
            .enqueue(object : Callback<CarResponse> {
                override fun onResponse(call: Call<CarResponse>, response: Response<CarResponse>) {
                    if (response.body() != null && response.body()?.poiList != null)
                        listener.onDataReady(response.body()!!.poiList)
                    else
                        listener.onError(Throwable("Response body is null"))
                }

                override fun onFailure(call: Call<CarResponse>, t: Throwable) {
                    listener.onError(t)
                }
            })
    }
}