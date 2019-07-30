package by.itacademy.korobchits.dz12

import by.itacademy.korobchits.dz6.Dz6Student
import io.reactivex.Completable
import io.reactivex.Observable

class StudentRepositoryRemote(private val api: StudentApi) : StudentRepository {

    override fun get(pageSize: Int, offset: Int): Observable<List<Dz6Student>> {
        return api.getStudents(pageSize, offset)
    }

    override fun getByFilterName(name: String, pageSize: Int, offset: Int): Observable<List<Dz6Student>> {
        return api.getStudentsByFilterName(pageSize, offset, "name LIKE '%$name%'")
    }

    override fun getById(id: String): Observable<Dz6Student> {
        return api.getStudentById(id)
    }

    override fun delete(id: String): Completable {
        return api.delete(id)
    }

    override fun insert(student: Dz6Student): Completable {
        return api.insert(student)
    }

    override fun update(student: Dz6Student): Completable {
        return api.update(student.id, student)
    }
}