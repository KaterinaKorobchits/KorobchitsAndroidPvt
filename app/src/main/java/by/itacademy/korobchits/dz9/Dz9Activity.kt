package by.itacademy.korobchits.dz9

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.FragmentActivity
import by.itacademy.classwork.cw9.entity.CoordParams
import by.itacademy.classwork.cw9.entity.Coordinate
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

class Dz9Activity : FragmentActivity(), Dz9Fragment.ClickListener, CarRepositoryResult, OnMapReadyCallback {

    private val carRepository: CarRepository = provideCarRepository()
    private val listPoi: MutableList<Poi> = mutableListOf()
    private lateinit var mMap: GoogleMap

    private var flagMapReady = false
    private var flagDataReady = false
    private lateinit var arrowBitmap: Bitmap
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        carRepository.getCarByCoord(CoordParams(Coordinate(2342.0, 342.0), Coordinate(3242.0, 3453.0)), this)

        arrowBitmap = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_upward_black_24dp)!!.toBitmap()
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.dz9container))

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            val fragmentOne = Dz9Fragment()
            transaction.replace(R.id.dz9container, fragmentOne)
            transaction.commit()
        }
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(this, "Oops! Something went wrong...", Toast.LENGTH_SHORT).show()
    }

    override fun onDataReady(data: List<Poi>) {
        listPoi.addAll(data)
        flagDataReady = true

        if (flagMapReady)
            drawCarsOnMap()
    }

    override fun onPoiItemClick(item: Poi) {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        mMap.clear()
        val car = LatLng(
            item.coordinate?.latitude!!,
            item.coordinate.longitude
        )
        mMap.addMarker(MarkerOptions().position(car).title("Hello"))
        mMap.addMarker(MarkerOptions().position(car).icon(BitmapDescriptorFactory.fromBitmap(arrowBitmap)).rotation(item.heading!!.toFloat()))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(car, 17f))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        flagMapReady = true

        if (flagDataReady)
            drawCarsOnMap()
    }

    private fun drawCarsOnMap() {
        val builder = LatLngBounds.builder()
        listPoi.forEach {
            val coord = LatLng(it.coordinate?.latitude!!, it.coordinate.longitude)
            mMap.addMarker(MarkerOptions().position(coord))
            builder.include(coord)
        }

        val bounds = builder.build()
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngBounds(
                bounds,
                resources.displayMetrics.widthPixels,
                resources.displayMetrics.heightPixels,
                resources.displayMetrics.widthPixels / 5
            )
        )
    }
}