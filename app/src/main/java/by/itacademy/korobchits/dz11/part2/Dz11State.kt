package by.itacademy.korobchits.dz11.part2

import by.itacademy.korobchits.dz9.entity.Poi

sealed class Dz11State {

    class LoadFailed(val throwable: Throwable) : Dz11State()
    class Data(val list: List<Poi>) : Dz11State()
    class Error(val throwable: Throwable) : Dz11State()
}