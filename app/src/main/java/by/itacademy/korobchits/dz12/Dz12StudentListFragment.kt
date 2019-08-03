package by.itacademy.korobchits.dz12

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.korobchits.R
import by.itacademy.korobchits.dz6.Dz6Student
import by.itacademy.korobchits.dz6.Dz6StudentAdapter
import by.itacademy.korobchits.dz8.AppPrefManager
import java.util.Timer
import kotlin.concurrent.schedule

class Dz12StudentListFragment : Fragment(), Dz12ListView, Dz6StudentAdapter.ClickListener {

    private lateinit var presenter: Dz12ListPresenter
    private lateinit var adapter: Dz6StudentAdapter
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private lateinit var dz12ProgressBar: ProgressBar
    private lateinit var dz12StudentList: RelativeLayout
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
        val view = inflater.inflate(R.layout.fragment_dz12_student_list, container, false)

        dz8Search = view.findViewById(R.id.dz8Search)
        dz12ProgressBar = view.findViewById(R.id.dz12ProgressBar)
        dz12StudentList = view.findViewById(R.id.dz12StudentList)
        val dz8AddButton = view.findViewById<ImageButton>(R.id.dz8AddButton)
        val dz8RecyclerView = view.findViewById<RecyclerView>(R.id.dz8RecycleView)

        presenter = Dz12ListPresenter()
        presenter.setView(this)

        dz8RecyclerView.setHasFixedSize(true)
        dz8RecyclerView.addItemDecoration(
            MarginItemDecoration(
                resources.getDimension(R.dimen.dz6RecycleItem).toInt(),
                resources.getDimension(R.dimen.dz6AddButton).toInt()
            )
        )

        val manager = LinearLayoutManager(context)
        dz8RecyclerView.layoutManager = manager
        dz8RecyclerView.isNestedScrollingEnabled = false
        adapter = Dz6StudentAdapter(emptyList(), this)
        dz8RecyclerView.adapter = adapter
        scrollListener = object : EndlessRecyclerViewScrollListener(manager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                presenter.loadMore(page)
            }
        }
        dz8RecyclerView.addOnScrollListener(scrollListener)

        dz8Search.addTextChangedListener(object : TextWatcher {

            var timer = Timer()
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                timer.cancel()
                timer = Timer()
                timer.schedule(500L) {
                    dz8SearchText = p0.toString()
                    scrollListener.resetState()
                    presenter.search(dz8SearchText)
                }
            }
        })

        dz8AddButton.setOnClickListener() {
            listener?.onAddButtonClick()
        }
        return view
    }

    override fun onResume() {
        super.onResume()

        prefManager = AppPrefManager(requireContext())
        val saveSearch = prefManager.getUserText()
        if (saveSearch != dz8SearchText) {
            dz8SearchText = saveSearch
            dz8Search.setText(dz8SearchText)
        }

        presenter.load(dz8SearchText)
    }

    override fun onPause() {
        super.onPause()
        prefManager.setUserText(dz8Search.text.toString())
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        presenter.detach()
    }

    override fun onStudentItemClick(item: Dz6Student) {
        listener?.onStudentItemClick(item.id)
    }

    override fun updateRecyclerList(list: List<Dz6Student>) {
        adapter.update(list)
    }

    fun updateRecyclerList() {
        presenter.updateRecyclerList()
        scrollListener.resetState()
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        dz12ProgressBar.visibility = View.VISIBLE
        dz12StudentList.visibility = View.GONE
    }

    override fun goneProgressBar() {
        dz12ProgressBar.visibility = View.GONE
        dz12StudentList.visibility = View.VISIBLE
    }

    interface Listener {
        fun onStudentItemClick(id: String)
        fun onAddButtonClick()
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