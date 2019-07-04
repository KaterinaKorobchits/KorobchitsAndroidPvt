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

    private lateinit var adapter: Dz6StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_list)

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
        adapter = Dz6StudentAdapter(Dz6StudentsStorage.getStudentsListFilled(), this)
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
                    adapter.update(Dz6StudentsStorage.filter(p0.toString()))
                }, 500)
            }
        })

        dz6AddButton.setOnClickListener() {
            startActivity(Dz6StudentEditActivity.getIntent(this@Dz6StudentListActivity))
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.update(Dz6StudentsStorage.getStudentsList())
    }

    override fun onStudentItemClick(item: Dz6Student) {
        startActivity(Dz6StudentDetailsActivity.getIntent(this@Dz6StudentListActivity, item.id))
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