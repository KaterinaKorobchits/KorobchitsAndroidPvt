package by.itacademy.korobchits.dz9

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.classwork.cw9.entity.Poi
import by.itacademy.korobchits.R

class Dz9PoiHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val dz9FleetType = itemView.findViewById<TextView>(R.id.dz9FleetType)
    private val dz9CoodLat = itemView.findViewById<TextView>(R.id.dz9CoordLat)
    private val dz9CoodLng = itemView.findViewById<TextView>(R.id.dz9CoordLng)

    fun bind(poi: Poi) {
        dz9FleetType.text = poi.fleetType?.name
        dz9CoodLat.text = poi.coordinate?.latitude.toString()
        dz9CoodLng.text = poi.coordinate?.longitude.toString()
    }
}