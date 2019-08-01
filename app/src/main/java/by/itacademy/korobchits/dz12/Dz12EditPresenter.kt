package by.itacademy.korobchits.dz12

import android.util.Patterns
import by.itacademy.korobchits.dz6.Dz6Student
import by.itacademy.korobchits.dz9.provideStudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz12EditPresenter(private val idStudent: String) {

    private var view: Dz12EditView? = null
    private val pattern = Patterns.WEB_URL
    private val repository = provideStudentRepository()
    private var disposable: Disposable? = null

    init {
        disposable = idStudent.let {
            repository
                .getById(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.goneProgressBar()
                    view?.show(it)
                }, { throwable ->
                    view?.goneProgressBar()
                })
        }
    }

    fun setView(view: Dz12EditView) {
        this.view = view
    }

    fun saveStudent(url: String, name: String, age: String) {
        view?.showProgressBar()
        disposable = repository
            .getById(idStudent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                updateStudent(
                    Dz6Student(
                        id = idStudent,
                        imageUrl = url,
                        name = name,
                        age = age.toInt()
                    )
                )
            }, {
                insertStudent(
                    Dz6Student(
                        id = System.currentTimeMillis().toString(),
                        imageUrl = url,
                        name = name,
                        age = age.toInt()
                    )
                )
            })
    }

    private fun updateStudent(student: Dz6Student) {
        disposable = repository
            .update(student)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.finish()
            }, { throwable ->
                view?.goneProgressBar()
                view?.showError(throwable.toString())
                view?.goBack()
            })
    }

    private fun insertStudent(student: Dz6Student) {
        disposable = repository
            .insert(student)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.finish()
            }, { throwable ->
                view?.goneProgressBar()
                view?.showError(throwable.toString())
                view?.goBack()
            })
    }

    fun validateData(url: String, name: String, age: String): Boolean {
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
        disposable?.dispose()
    }
}