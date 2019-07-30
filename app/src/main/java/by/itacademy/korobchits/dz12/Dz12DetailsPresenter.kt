package by.itacademy.korobchits.dz12

import by.itacademy.korobchits.dz6.Dz6Student
import by.itacademy.korobchits.dz9.provideStudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz12DetailsPresenter(private val idStudent: String) {

    private var view: Dz12DetailsView? = null
    private var user: Dz6Student? = null
    private val repository = provideStudentRepository()
    private var disposable: Disposable? = null

    init {
        disposable = repository
            .getById(idStudent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                user = it
                view?.show(it)
            }, { throwable ->
                view?.showError(throwable.toString())
                view?.goBack()
            })
    }

    fun setView(view: Dz12DetailsView) {
        this.view = view
    }

    fun deleteStudent() {
        view?.showProgressBar()
        disposable = repository
            .delete(idStudent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.finish()
            }, { throwable ->
                view?.showError(throwable.toString())
                view?.goBack()
            })
    }

    fun detach() {
        this.view = null
        disposable?.dispose()
    }
}