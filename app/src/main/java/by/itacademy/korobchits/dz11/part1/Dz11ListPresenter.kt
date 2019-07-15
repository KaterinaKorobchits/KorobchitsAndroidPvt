package by.itacademy.korobchits.dz11.part1

import by.itacademy.korobchits.dz6.Dz6StudentsStorage

class Dz11ListPresenter {

    private var view: Dz11ListView? = null

    fun setView(view: Dz11ListView) {
        this.view = view
    }

    fun load() {
        view?.show(Dz6StudentsStorage.getStudentsList())
    }

    fun search(searchText: String) {
        view?.show(Dz6StudentsStorage.filter(searchText))
    }

    fun detach() {
        this.view = null
    }
}