package by.itacademy.korobchits.dz11.part2

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.itacademy.korobchits.dz9.CarRepository
import by.itacademy.korobchits.dz9.entity.CoordParams
import by.itacademy.korobchits.dz9.entity.Coordinate
import by.itacademy.korobchits.dz9.entity.Poi
import by.itacademy.korobchits.dz9.provideCarRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz11ViewModel : ViewModel() {

    val state: MutableLiveData<Dz11State> by lazy(LazyThreadSafetyMode.NONE) { MutableLiveData<Dz11State>() }
    val selectedItem: MutableLiveData<Poi> by lazy(LazyThreadSafetyMode.NONE) { MutableLiveData<Poi>() }
    private var disposable: Disposable? = null
    private val carRepository: CarRepository = provideCarRepository()
    private var data: List<Poi> = mutableListOf()

    init {
        disposable = carRepository
            .getCars(CoordParams(Coordinate(2342.0, 342.0), Coordinate(3242.0, 3453.0)))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                data = it.poiList
                state.value = Dz11State.Data(data)
                Log.e("AAA", "load success")
            }, { throwable ->
                state.value = Dz11State.LoadFailed(throwable)
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    fun clickItem(item: Poi) {
        selectedItem.value = item
    }
}