package by.itacademy.korobchits.dz11.part1

import by.itacademy.korobchits.dz6.Dz6Student
import by.itacademy.korobchits.dz6.Dz6StudentsStorage

class Dz11EditPresenter {
    private var view: Dz11EditView? = null

    fun setView(view: Dz11EditView) {
        this.view = view
    }

    fun getStudentById(idStudent: String?) {
        val user = idStudent?.let { Dz6StudentsStorage.getStudentById(it) }
        if (user == null)
            view?.goBack()
        else
            view?.show(user)
    }

    fun saveStudent(url: String, name: String, age: String, id: String = System.currentTimeMillis().toString()) {
        if (view?.validateData(url, name, age)!!)
            Dz6StudentsStorage.addStudent(
                Dz6Student(
                    id = id,
                    imageUrl = url,
                    name = name,
                    age = age.toInt()
                )
            )
    }

    fun detach() {
        this.view = null
    }
}