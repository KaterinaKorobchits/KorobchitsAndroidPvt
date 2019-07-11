package by.itacademy.korobchits.dz8

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.korobchits.R
import by.itacademy.korobchits.dz6.Dz6Student
import by.itacademy.korobchits.dz6.Dz6StudentAdapter
import by.itacademy.korobchits.dz6.Dz6StudentsStorage
import kotlinx.android.synthetic.main.fragment_dz8_student_list.view.dz8Search
import kotlinx.android.synthetic.main.fragment_dz8_student_list.view.dz8AddButton

class Dz8StudentListFragment : Fragment(), Dz6StudentAdapter.ClickListener {

    private lateinit var adapter: Dz6StudentAdapter
    private lateinit var prefManager: AppPrefManager
    private var dz8SearchText: String = ""
    private lateinit var dz8Search: EditText
    private var listener: Listener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener)
            listener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dz8_student_list, container, false)

        val dz8RecyclerView = view.findViewById<RecyclerView>(R.id.dz8RecycleView)
        dz8RecyclerView.setHasFixedSize(true)

        dz8RecyclerView.addItemDecoration(
            MarginItemDecoration(
                resources.getDimension(R.dimen.dz6RecycleItem).toInt(),
                resources.getDimension(R.dimen.dz6AddButton).toInt()
            )
        )
        dz8RecyclerView.layoutManager = LinearLayoutManager(context)
        dz8RecyclerView.isNestedScrollingEnabled = false
        adapter = Dz6StudentAdapter(Dz6StudentsStorage.getStudentsList(), this)
        dz8RecyclerView.adapter = adapter

        dz8Search = view.dz8Search
        dz8Search.addTextChangedListener(object : TextWatcher {

            var timer: Handler? = null
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                timer?.removeCallbacks(null)
            }

            override fun afterTextChanged(p0: Editable?) {
                timer = Handler()
                timer?.postDelayed({
                    dz8SearchText = p0.toString()
                    adapter.update(Dz6StudentsStorage.filter(dz8SearchText))
                }, 500)
            }
        })

        view.dz8AddButton.setOnClickListener() {
            listener?.onAddButtonClick()
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onResume() {
        super.onResume()
        prefManager = AppPrefManager(requireContext())
        val saveSearch = prefManager.getUserText()
        if (saveSearch != dz8SearchText) {
            dz8SearchText = saveSearch
            dz8Search.setText(dz8SearchText)
            updateRecyclerList()
        }
    }

    override fun onPause() {
        super.onPause()
        prefManager.setUserText(dz8Search.text.toString())
    }

    override fun onStudentItemClick(item: Dz6Student) {
        listener?.onStudentItemClick(item.id)
    }

    interface Listener {
        fun onStudentItemClick(id: String)
        fun onAddButtonClick()
    }

    fun updateRecyclerList() {
        adapter.update(Dz6StudentsStorage.filter(dz8SearchText))
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