package by.itacademy.korobchits

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.itacademy.korobchits.dz0.Dz0Activity
import by.itacademy.korobchits.dz1.Dz1Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonDz0.setOnClickListener() {
            val intent = Intent(this@MainActivity, Dz0Activity::class.java)
            startActivity(intent)
        }

        buttonDz1.setOnClickListener() {
            val intent = Intent(this@MainActivity, Dz1Activity::class.java)
            startActivity(intent)
        }
    }


}