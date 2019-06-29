package by.itacademy.korobchits.dz6

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.korobchits.R

class Dz6StudentListActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_list)

        Dz6StudentList.getInstance().listStudents = mutableListOf(
            Dz6Student(100001, "", "Barabusik", 32),
            Dz6Student(100002, "", "Katrinka", 22),
            Dz6Student(100003, "", "Savelia", 12),
            Dz6Student(100004, "", "Maksimka", 26),
            Dz6Student(100005, "", "Paulina", 27),
            Dz6Student(100006, "", "Shantony", 18),
            Dz6Student(100007, "", "Mamasita", 33),
            Dz6Student(100008, "", "Pethouse", 29),
            Dz6Student(100009, "", "LuckyDog", 31),
            Dz6Student(100010, "", "Angleina", 30),
            Dz6Student(100011, "", "Evgenyshka", 32)
        )

        val dz6RecyclerView = findViewById<RecyclerView>(R.id.dz6RecycleView)
        dz6RecyclerView.setHasFixedSize(true)
        dz6RecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        dz6RecyclerView.layoutManager = LinearLayoutManager(this)
        dz6RecyclerView.isNestedScrollingEnabled = false
        dz6RecyclerView.adapter = Dz6StudentAdapter(Dz6StudentList.getInstance().listStudents)
    }
}