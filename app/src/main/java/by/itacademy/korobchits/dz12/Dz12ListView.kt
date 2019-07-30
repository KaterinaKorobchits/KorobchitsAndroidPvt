package by.itacademy.korobchits.dz12

import by.itacademy.korobchits.dz6.Dz6Student

interface Dz12ListView {
    fun updateRecyclerList(list: List<Dz6Student>)
    fun showProgressBar()
    fun goneProgressBar()
    fun showError(error: String)
}