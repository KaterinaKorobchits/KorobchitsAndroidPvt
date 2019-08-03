package by.itacademy.korobchits.dz12

import by.itacademy.korobchits.dz6.Dz6Student

interface Dz12DetailsView {
    fun show(user: Dz6Student)
    fun goBack()
    fun finish()
    fun showProgressBar()
    fun showError(error: String)
}