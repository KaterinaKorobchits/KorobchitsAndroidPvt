package by.itacademy.korobchits.dz12

import by.itacademy.korobchits.dz6.Dz6Student

interface Dz12EditView {
    fun show(user: Dz6Student)
    fun showValidateError(error: String)
    fun showError(error: String)
    fun goBack()
    fun finish()
    fun showProgressBar()
    fun goneProgressBar()
}