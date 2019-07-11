package by.itacademy.korobchits.dz6

object Dz6StudentsStorage {

    private var listStudents: MutableList<Dz6Student> = mutableListOf()

    init {
        listStudents = getStudentsListFilled()
    }

    fun getStudentsList(): MutableList<Dz6Student> {
        return listStudents
    }

    fun getStudentsListFilled(): MutableList<Dz6Student> {
        fillList()
        return listStudents
    }

    private fun fillList() {
        listStudents = mutableListOf(
            Dz6Student(
                "100001", "https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_960_720.png",
                "Pavel Ivanov", 32
            ), Dz6Student(
                "100002L",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvILzsUEt_-9x1B9LkuLs6V_NWRVSNBW_Np-uH7TEKXG8uhyvY",
                "Katrinka Lovely",
                22
            ), Dz6Student(
                "100003", "https://avatars.mds.yandex.net/get-pdb/49816/cc376ea8-22f7-4777-8d11-f5d23a1ae1c2/orig",
                "Savelia KingDom",
                2
            ), Dz6Student(
                "100004", "https://pp.userapi.com/c854020/v854020212/21ce0/-xgCHrEFLfI.jpg",
                "Maksimka Lucky", 6
            ), Dz6Student(
                "100005", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvWddTef4q0yfqEV_f_AfVfkrkMEE67IwwXLblS2HTRRfzhSPr",
                "Paulina LuckyDog", 3
            ), Dz6Student(
                "100006", "https://pp.userapi.com/c855720/v855720610/224eb/Z4dojAnk2vk.jpg",
                "Shantony", 1
            ), Dz6Student(
                "100007", "https://99px.ru/sstorage/53/2013/06/tmb_72173_2040.jpg",
                "Richi", 1
            ), Dz6Student(
                "100008", "https://pp.userapi.com/c851424/v851424845/1214e3/05Snnwa3dhA.jpg",
                "Babi", 4
            ), Dz6Student(
                "100009", "https://99px.ru/sstorage/53/2013/06/tmb_72173_2040.jpg",
                "Angleina Friendly", 30
            )
        )
    }

    fun getStudentById(id: String): Dz6Student? {
        return listStudents.find { it.id == id }
    }

    fun addStudent(student: Dz6Student) {
        if (getStudentById(student.id) == null)
            listStudents.add(student)
        else {
            listStudents.remove(getStudentById(student.id))
            listStudents.add(student)
        }
    }

    fun removeStudent(student: Dz6Student) {
        listStudents.remove(student)
    }

    fun filter(search: String): List<Dz6Student> =
        listStudents.filter { it.name.toUpperCase().contains(search.toUpperCase()) }
}