package by.itacademy.korobchits.dz6

class Dz6StudentList private constructor() {

    var listStudents = mutableListOf<Dz6Student>()

    companion object {
        private val instance: Dz6StudentList = Dz6StudentList()

        fun getInstance(): Dz6StudentList {
            return instance
        }
    }
}