package by.itacademy.korobchits.dz11.part1

import by.itacademy.korobchits.dz6.Dz6Student

interface Dz11EditView {
    fun show(user: Dz6Student)
    fun showValidateError(error: String)
    fun goBack()
}