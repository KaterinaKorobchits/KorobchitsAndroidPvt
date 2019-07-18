package by.itacademy.korobchits.dz11.part1

import android.util.Patterns
import by.itacademy.korobchits.dz6.Dz6Student

class Dz11EditPresenter(private val idStudent: String) {
    private var view: Dz11EditView? = null
    private val pattern = Patterns.WEB_URL

    fun setView(view: Dz11EditView) {
        this.view = view
    }

    fun getStudentById() {
        val user = Dz11StudentsStorage.getStudentById(idStudent)
        if (user == null)
            view?.goBack()
        else
            view?.show(user)
    }

    fun saveStudent(url: String, name: String, age: String) {
        if (validateData(url, name, age))
            when (Dz11StudentsStorage.getStudentById(idStudent)) {
                null -> {
                    Dz11StudentsStorage.addStudent(
                        Dz6Student(
                            id = System.currentTimeMillis().toString(),
                            imageUrl = url,
                            name = name,
                            age = age.toInt()
                        )
                    )
                }
                else -> {
                    Dz11StudentsStorage.addStudent(
                        Dz6Student(
                            id = idStudent,
                            imageUrl = url,
                            name = name,
                            age = age.toInt()
                        )
                    )
                }
            }
    }

    private fun validateData(url: String, name: String, age: String): Boolean {
        if (!pattern.matcher(url).matches()) {
            view?.showValidateError("Image URL: Not valid URL")
            return false
        } else if (name.isEmpty()) {
            view?.showValidateError("Name: Must be filled in")
            return false
        } else if (age.isEmpty()) {
            view?.showValidateError("Age: Must be filled in")
            return false
        }
        return true
    }

    fun detach() {
        this.view = null
    }
}