package by.itacademy.korobchits.dz12

import by.itacademy.korobchits.dz6.Dz6Student
import io.reactivex.Completable
import io.reactivex.Observable

interface StudentRepository {

    fun get(pageSize: Int, offset: Int): Observable<List<Dz6Student>>

    fun getByFilterName(name: String, pageSize: Int, offset: Int): Observable<List<Dz6Student>>

    fun getById(id: String): Observable<Dz6Student>

    fun delete(id: String): Completable

    fun insert(student: Dz6Student): Completable

    fun update(student: Dz6Student): Completable
}