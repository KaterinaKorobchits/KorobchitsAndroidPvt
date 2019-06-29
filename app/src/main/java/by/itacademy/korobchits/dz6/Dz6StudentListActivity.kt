package by.itacademy.korobchits.dz6

import android.app.Activity
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.korobchits.R

class Dz6StudentListActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_list)

        Dz6StudentList.getInstance().listStudents = mutableListOf(
            Dz6Student(100001, "https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_960_720.png", "Barabusik", 32),
            Dz6Student(100002, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvILzsUEt_-9x1B9LkuLs6V_NWRVSNBW_Np-uH7TEKXG8uhyvY", "Katrinka", 22),
            Dz6Student(100003, "https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_968_720.png", "Savelia", 12),
            Dz6Student(100004, "https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_960_720.png", "Maksimka", 26),
            Dz6Student(100005, "https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_960_720.png", "Paulina", 27),
            Dz6Student(100006, "https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_965_720.png", "Shantony", 18),
            Dz6Student(100007, "https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_960_720.png", "Mamasita", 33),
            Dz6Student(100008, "https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_960_720.png", "Pethouse", 29),
            Dz6Student(100009, "https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_969_720.png", "LuckyDog", 31),
            Dz6Student(100010, "https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_960_720.png", "Angleina", 30),
            Dz6Student(100011, "https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_960_720.png", "Evgenyshka", 32)
        )

        val dz6RecyclerView = findViewById<RecyclerView>(R.id.dz6RecycleView)
        dz6RecyclerView.setHasFixedSize(true)
        dz6RecyclerView.addItemDecoration(MarginItemDecoration(10))
        dz6RecyclerView.layoutManager = LinearLayoutManager(this)
        dz6RecyclerView.isNestedScrollingEnabled = false
        dz6RecyclerView.adapter = Dz6StudentAdapter(Dz6StudentList.getInstance().listStudents)
    }
}

class MarginItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0)
                top = spaceHeight
            left = spaceHeight *4
            right = spaceHeight * 4
            bottom = spaceHeight * 3
        }
    }
}