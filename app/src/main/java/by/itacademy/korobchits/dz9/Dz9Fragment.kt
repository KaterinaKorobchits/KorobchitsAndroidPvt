package by.itacademy.korobchits.dz9

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.classwork.cw9.entity.CoordParams
import by.itacademy.classwork.cw9.entity.Coordinate
import by.itacademy.classwork.cw9.entity.Poi
import by.itacademy.korobchits.R

class Dz9Fragment : Fragment(), Dz9PoiAdapter.ClickListener, CarRepositoryResult {

    private val carRepository: CarRepository = provideCarRepository()
    private lateinit var adapter: Dz9PoiAdapter
    private val listPoi: MutableList<Poi> = mutableListOf()
    private var listener: ClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ClickListener)
            listener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dz9, container, false)

        carRepository.getCarByCoord(CoordParams(Coordinate(2342.0, 342.0), Coordinate(3242.0, 3453.0)), this)

        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(context, "Oops! Something went wrong...", Toast.LENGTH_SHORT).show()
    }

    override fun onDataReady(data: List<Poi>) {
        listPoi.addAll(data)

        filledAdapter()
    }

    override fun onPoiItemClick(item: Poi) {
        listener?.onPoiItemClick(item)
    }

    private fun filledAdapter() {
        val dz9RecyclerView = view!!.findViewById<RecyclerView>(R.id.dz9RecyclerView)
        dz9RecyclerView.setHasFixedSize(true)

        dz9RecyclerView.layoutManager = LinearLayoutManager(context)
        dz9RecyclerView.isNestedScrollingEnabled = false
        dz9RecyclerView.addItemDecoration(MyDividerItemDecoration(ContextCompat.getColor(context!!, R.color.dz9)))
        adapter = Dz9PoiAdapter(listPoi, this@Dz9Fragment)
        dz9RecyclerView.adapter = adapter
    }

    interface ClickListener {
        fun onPoiItemClick(item: Poi)
    }
}