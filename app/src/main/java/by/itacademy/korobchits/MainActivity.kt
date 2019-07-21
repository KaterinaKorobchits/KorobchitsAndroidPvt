package by.itacademy.korobchits

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.itacademy.korobchits.dz0.Dz0Activity
import by.itacademy.korobchits.dz1.Dz1Activity
import by.itacademy.korobchits.dz11.part2.Dz11Activity
import by.itacademy.korobchits.dz2.Dz2Activity
import by.itacademy.korobchits.dz2.Dz2LoginActivity
import by.itacademy.korobchits.dz3.Dz3Activity
import by.itacademy.korobchits.dz4.Dz4Activity
import by.itacademy.korobchits.dz6.Dz6StudentListActivity
import by.itacademy.korobchits.dz5.Dz5Activity
import by.itacademy.korobchits.dz5.Dz5ActivityOwl
import by.itacademy.korobchits.dz9.Dz9Activity
import by.itacademy.korobchits.dz8.Dz8Activity
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

        buttonDz2part1.setOnClickListener() {
            val intent = Intent(this@MainActivity, Dz2LoginActivity::class.java)
            startActivity(intent)
        }

        buttonDz2part2.setOnClickListener() {
            val intent = Intent(this@MainActivity, Dz2Activity::class.java)
            startActivity(intent)
        }

        buttonDz3.setOnClickListener() {
            val intent = Intent(this@MainActivity, Dz3Activity::class.java)
            startActivity(intent)
        }

        buttonDz4.setOnClickListener() {
            val intent = Intent(this@MainActivity, Dz4Activity::class.java)
            startActivity(intent)
        }

        buttonDz5part1.setOnClickListener() {
            val intent = Intent(this@MainActivity, Dz5Activity::class.java)
            startActivity(intent)
        }

        buttonDz5part2.setOnClickListener() {
            val intent = Intent(this@MainActivity, Dz5ActivityOwl::class.java)
            startActivity(intent)
        }

        buttonDz6.setOnClickListener() {
            val intent = Intent(this@MainActivity, Dz6StudentListActivity::class.java)
            startActivity(intent)
        }

        buttonDz8.setOnClickListener() {
            val intent = Intent(this@MainActivity, Dz8Activity::class.java)
            startActivity(intent)
        }

        buttonDz9.setOnClickListener() {
            val intent = Intent(this@MainActivity, Dz9Activity::class.java)
            startActivity(intent)
        }

        buttonDz11part1.setOnClickListener() {
            val intent = Intent(this@MainActivity, by.itacademy.korobchits.dz11.part1.Dz11Activity::class.java)
            startActivity(intent)
        }

        buttonDz11part2.setOnClickListener() {
            val intent = Intent(this@MainActivity, Dz11Activity::class.java)
            startActivity(intent)
        }
    }
}