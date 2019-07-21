package by.itacademy.korobchits.dz11.part1

import by.itacademy.korobchits.dz6.Dz6Student

class Dz11DetailsPresenter(private val idStudent: String?) {

    private var view: Dz11DetailsView? = null
    private var user: Dz6Student? = null

    fun setView(view: Dz11DetailsView) {
        this.view = view
    }

    fun getStudentById() {
        user = idStudent?.let { Dz11StudentsStorage.getStudentById(it) }
        if (user == null)
            view?.goBack()
        else
            view?.show(user!!)
    }

    fun deleteStudent() {
        idStudent?.let { Dz11StudentsStorage.removeStudent(it) }
    }

    fun detach() {
        this.view = null
    }
}