package by.itacademy.korobchits.dz11.part1

import by.itacademy.korobchits.dz6.Dz6Student
import by.itacademy.korobchits.dz6.Dz6StudentsStorage

class Dz11DetailsPresenter {

    private var view: Dz11DetailsView? = null
    private var user: Dz6Student? = null

    fun setView(view: Dz11DetailsView) {
        this.view = view
    }

    fun getStudentById(idStudent: String?): Dz6Student? {
        user = idStudent?.let { Dz6StudentsStorage.getStudentById(it) }
        if (user == null)
            view?.goBack()
        else
            view?.show(user!!)
        return user
    }

    fun deleteStudent() {
        Dz6StudentsStorage.removeStudent(user!!)
    }

    fun detach() {
        this.view = null
    }
}