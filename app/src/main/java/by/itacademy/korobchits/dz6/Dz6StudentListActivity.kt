package by.itacademy.korobchits.dz6

import android.app.Activity
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.korobchits.R
import kotlinx.android.synthetic.main.activity_dz6_student_list.*

class Dz6StudentListActivity : Activity(), Dz6StudentAdapter.ClickListener {

    private var adapter: Dz6StudentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_list)

        Dz6StudentList.getInstance().listStudents = mutableListOf(
            Dz6Student(
                100001, "https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_960_720.png",
                "Pavel Ivanov", 32
            ), Dz6Student(
                100002L,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvILzsUEt_-9x1B9LkuLs6V_NWRVSNBW_Np-uH7TEKXG8uhyvY",
                "Katrinka Lovely",
                22
            ), Dz6Student(
                100003, "https://avatars.mds.yandex.net/get-pdb/49816/cc376ea8-22f7-4777-8d11-f5d23a1ae1c2/orig",
                "Savelia KingDom",
                2
            ), Dz6Student(
                100004, "https://pp.userapi.com/c854020/v854020212/21ce0/-xgCHrEFLfI.jpg",
                "Maksimka Lucky", 6
            ), Dz6Student(
                100005, "https://pp.userapi.com/c847120/v847120742/1ebb45/HZlXIlNHrUQ.jpg",
                "Paulina LuckyDog", 3
            ), Dz6Student(
                100006, "https://pp.userapi.com/c855720/v855720610/224eb/Z4dojAnk2vk.jpg",
                "Shantony", 1
            ), Dz6Student(
                100007, "https://pp.userapi.com/c849024/v849024233/dd8c0/6oABTJlLy0k.jpg",
                "Richi", 1
            ), Dz6Student(
                100008, "https://pp.userapi.com/c851424/v851424845/1214e3/05Snnwa3dhA.jpg",
                "Babi", 4
            ), Dz6Student(
                100009, "https://99px.ru/sstorage/53/2013/06/tmb_72173_2040.jpg",
                "Angleina Friendly", 30
            )
        )

        val dz6RecyclerView = findViewById<RecyclerView>(R.id.dz6RecycleView)
        dz6RecyclerView.setHasFixedSize(true)
        dz6RecyclerView.addItemDecoration(
            MarginItemDecoration(
                resources.getDimension(R.dimen.dz6RecycleItem).toInt(),
                resources.getDimension(R.dimen.dz6AddButton).toInt()
            )
        )
        dz6RecyclerView.layoutManager = LinearLayoutManager(this)
        dz6RecyclerView.isNestedScrollingEnabled = false
        adapter = Dz6StudentAdapter(Dz6StudentList.getInstance().listStudents.toMutableList(), this)
        dz6RecyclerView.adapter = adapter

        dz6Search.addTextChangedListener(object : TextWatcher {

            var timer: Handler? = null
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                timer?.removeCallbacks(null)
            }

            override fun afterTextChanged(p0: Editable?) {
                timer = Handler()
                timer?.postDelayed({
                    filter(p0.toString())
                }, 500)
            }
        })

        dz6AddButton.setOnClickListener() {
            startActivity(Dz6StudentEditActivity.getIntent(this@Dz6StudentListActivity))
        }
    }

    override fun onStart() {
        super.onStart()
        adapter?.update()
    }

    override fun onStudentItemClick(item: Dz6Student) {
        startActivity(Dz6StudentDetailsActivity.getIntent(this@Dz6StudentListActivity, item.id))
    }

    private fun filter(search: String) {

        val filterList: List<Dz6Student> =
            Dz6StudentList.getInstance().listStudents.filter { it.name.toUpperCase().contains(search.toUpperCase()) }
        adapter?.filter(filterList)
    }
}

class MarginItemDecoration(private val spaceHeight: Int, private val spaceBottomLast: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            bottom = if (parent.getChildAdapterPosition(view) == state.itemCount - 1)
                spaceBottomLast + spaceHeight else spaceHeight
        }
    }
}