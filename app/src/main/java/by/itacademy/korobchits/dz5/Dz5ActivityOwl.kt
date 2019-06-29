package by.itacademy.korobchits.dz5

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import by.itacademy.korobchits.R
import kotlinx.android.synthetic.main.activity_dz5owl.*

class Dz5ActivityOwl : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5owl)
    }

    override fun onResume() {
        super.onResume()
        (imageOwl.background as AnimationDrawable).start()
    }

    override fun onPause() {
        super.onPause()
        (imageOwl.background as AnimationDrawable).stop()
    }
}