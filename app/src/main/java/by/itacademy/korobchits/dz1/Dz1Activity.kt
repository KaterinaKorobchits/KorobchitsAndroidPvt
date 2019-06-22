package by.itacademy.korobchits.dz1

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import by.itacademy.korobchits.R

class Dz1Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dz1_activity)

        findViewById<View>(R.id.austria).setOnClickListener() {
            setCountryName(R.id.austriaText, "AUSTRIA")
        }

        findViewById<View>(R.id.poland).setOnClickListener() {
            setCountryName(R.id.polandText, "POLAND")
        }

        findViewById<View>(R.id.italy).setOnClickListener() {
            setCountryName(R.id.italyText, "ITALY")
        }

        findViewById<View>(R.id.colombia).setOnClickListener() {
            setCountryName(R.id.colombiaText, "COLOMBIA")
        }

        findViewById<View>(R.id.madagascar).setOnClickListener() {
            setCountryName(R.id.madagascarText, "MADAGASKAR")
        }

        findViewById<View>(R.id.thailand).setOnClickListener() {
            setCountryName(R.id.thailandText, "THAILAND")
        }

        findViewById<View>(R.id.denmark).setOnClickListener() {
            setCountryName(R.id.denmarkText, "DENMARK")
        }
    }

    private fun setCountryName(id: Int, name: String) {
        val view = findViewById<View>(id) as? TextView
        view?.setText(name)
        view?.visibility = View.VISIBLE
    }
}
