package by.itacademy.korobchits.dz12

import by.itacademy.korobchits.dz6.Dz6Student
import by.itacademy.korobchits.dz9.provideStudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz12ListPresenter {

    private val PAGE_SIZE = 10
    private var view: Dz12ListView? = null
    private val repository = provideStudentRepository()
    private val listStudents: MutableList<Dz6Student> = mutableListOf()
    private var disposable: Disposable? = null
    private var searchText: String = ""

    fun load(searchText: String) {
        this.searchText = searchText
        if (searchText.isEmpty())
            disposable = repository
                .get(PAGE_SIZE, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listStudents.clear()
                    listStudents.addAll(it)
                    view?.goneProgressBar()
                    view?.updateRecyclerList(listStudents)
                }, { throwable ->
                    view?.goneProgressBar()
                    view?.showError(throwable.toString())
                })
        else
            disposable = repository
                .getByFilterName(searchText, PAGE_SIZE, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listStudents.clear()
                    listStudents.addAll(it)
                    view?.goneProgressBar()
                    view?.updateRecyclerList(listStudents)
                }, { throwable ->
                    view?.goneProgressBar()
                    view?.showError(throwable.toString())
                })
    }

    fun setView(view: Dz12ListView) {
        this.view = view
    }

    fun updateRecyclerList() {
        view?.showProgressBar()
        load(searchText)
    }

    fun loadMore(offset: Int) {
        if (searchText.isEmpty())
            disposable = repository
                .get(PAGE_SIZE, offset*PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listStudents.addAll(it)
                    view?.updateRecyclerList(listStudents)
                }, { throwable ->
                    view?.showError(throwable.toString())
                })
        else
            disposable = repository
                .getByFilterName(searchText, PAGE_SIZE, offset*PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listStudents.addAll(it)
                    view?.updateRecyclerList(listStudents)
                }, { throwable ->
                    view?.showError(throwable.toString())
                })
    }

    fun search(searchText: String) {
        if (searchText == this.searchText) return
        this.searchText = searchText

        if (searchText.isEmpty())
            disposable = repository
                .get(PAGE_SIZE, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listStudents.clear()
                    listStudents.addAll(it)
                    view?.updateRecyclerList(listStudents)
                }, { throwable ->
                    view?.showError(throwable.toString())
                })
        else
            disposable = repository
                .getByFilterName(searchText, PAGE_SIZE, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listStudents.clear()
                    listStudents.addAll(it)
                    view?.updateRecyclerList(listStudents)
                }, { throwable ->
                    view?.showError(throwable.toString())
                })
    }

    fun detach() {
        this.view = null
        disposable?.dispose()
    }
}