package by.itacademy.korobchits.dz11.part2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.itacademy.classwork.cw9.entity.CoordParams
import by.itacademy.classwork.cw9.entity.Coordinate
import by.itacademy.classwork.cw9.entity.Poi
import by.itacademy.korobchits.dz9.CarRepository
import by.itacademy.korobchits.dz9.CarRepositoryResult
import by.itacademy.korobchits.dz9.provideCarRepository

class Dz11ViewModel : ViewModel(), CarRepositoryResult {

    val state: MutableLiveData<Dz11State> by lazy(LazyThreadSafetyMode.NONE) { MutableLiveData<Dz11State>() }
    val selectedItem: MutableLiveData<Poi> by lazy(LazyThreadSafetyMode.NONE) { MutableLiveData<Poi>() }
    private val carRepository: CarRepository = provideCarRepository()
    private lateinit var data: List<Poi>

    init {
        carRepository.getCarByCoord(CoordParams(Coordinate(2342.0, 342.0), Coordinate(3242.0, 3453.0)), this)
    }

    override fun onDataReady(data: List<Poi>) {
        state.value = Dz11State.Data(data)
        this.data = data
    }

    override fun onError(throwable: Throwable) {
        state.value = Dz11State.LoadFailed(throwable)
    }

    /*fun load() {
        if (state.value == null)
            carRepository.getCarByCoord(CoordParams(Coordinate(2342.0, 342.0), Coordinate(3242.0, 3453.0)), this)
    }*/

    fun clickItem(item: Poi) {
        selectedItem.value = item
    }
}