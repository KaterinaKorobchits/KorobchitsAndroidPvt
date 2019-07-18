package by.itacademy.korobchits.dz11.part2

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import by.itacademy.classwork.cw9.entity.Poi
import by.itacademy.korobchits.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior

class Dz11Activity : FragmentActivity(), OnMapReadyCallback {

    private lateinit var viewModel: Dz11ViewModel
    private lateinit var map: GoogleMap
    private lateinit var arrowBitmap: Bitmap
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private val observerState = Observer<Dz11State> {
        when (it) {
            is Dz11State.LoadFailed -> {
                showError(it.throwable)
            }
            is Dz11State.Data -> {
                drawCarsOnMap(it.list)
            }
        }
    }

    private val observerSelectedItem = Observer<Poi> {
        if (it != null)
            focusOnTheCar(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9)

        viewModel = ViewModelProviders.of(this).get(Dz11ViewModel::class.java)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        arrowBitmap = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_upward_black_24dp)!!.toBitmap()
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.dz9container))

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            val fragmentOne = Dz11Fragment()
            transaction.replace(R.id.dz9container, fragmentOne)
            transaction.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.state.removeObserver(observerState)
        viewModel.selectedItem.removeObserver(observerSelectedItem)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, "Oops! Something went wrong...", Toast.LENGTH_SHORT).show()
    }

    private fun focusOnTheCar(item: Poi) {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        map.clear()
        val car = LatLng(
            item.coordinate?.latitude!!,
            item.coordinate.longitude
        )
        map.addMarker(MarkerOptions().position(car).title("Hello"))
        map.addMarker(MarkerOptions().position(car).icon(BitmapDescriptorFactory.fromBitmap(arrowBitmap)).rotation(item.heading!!.toFloat()))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(car, 17f))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        viewModelObserve()
    }

    private fun viewModelObserve() {
        viewModel.state.observeForever(observerState)
        viewModel.selectedItem.observeForever(observerSelectedItem)
    }

    private fun drawCarsOnMap(listPoi: List<Poi>) {
        val builder = LatLngBounds.builder()
        listPoi.forEach {
            val coord = LatLng(it.coordinate?.latitude!!, it.coordinate.longitude)
            map.addMarker(MarkerOptions().position(coord))
            builder.include(coord)
        }

        val bounds = builder.build()
        map.moveCamera(
            CameraUpdateFactory.newLatLngBounds(
                bounds,
                resources.displayMetrics.widthPixels,
                resources.displayMetrics.heightPixels,
                resources.displayMetrics.widthPixels / 5
            )
        )
    }
}