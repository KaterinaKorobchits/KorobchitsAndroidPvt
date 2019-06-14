package by.itacademy.korobchits.dz0

import android.app.Activity
import android.os.Bundle
import by.itacademy.korobchits.R
import kotlinx.android.synthetic.main.dz0_activity.*

class Dz0Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dz0_activity)

        button.setOnClickListener {
            replaceTextViews()
        }

        firstText.setOnClickListener {
            replaceTextViews()
        }

        secondText.setOnClickListener {
            replaceTextViews()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    fun replaceTextViews() {
        val valueFirstText = firstText.text
        val valueSecondText = secondText.text
        val valueFirstTextBackground = firstText.background
        val valueSecondTextBackground = secondText.background
        firstText.text = valueSecondText
        firstText.background = valueSecondTextBackground
        secondText.text = valueFirstText
        secondText.background = valueFirstTextBackground
    }
}