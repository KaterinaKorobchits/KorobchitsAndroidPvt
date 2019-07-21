package by.itacademy.korobchits.dz11.part1

class Dz11ListPresenter {

    private var view: Dz11ListView? = null

    fun setView(view: Dz11ListView) {
        this.view = view
    }

    fun load() {
        view?.show(Dz11StudentsStorage.getStudentsList())
    }

    fun search(searchText: String) {
        view?.show(Dz11StudentsStorage.filter(searchText))
    }

    fun detach() {
        this.view = null
    }
}