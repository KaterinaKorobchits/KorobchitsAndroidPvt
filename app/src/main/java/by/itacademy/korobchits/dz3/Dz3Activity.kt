package by.itacademy.korobchits.dz3

import android.app.Activity
import android.os.Bundle
import by.itacademy.korobchits.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_dz3.*

class Dz3Activity : Activity() {
    private val url = "https://99px.ru/sstorage/53/2013/06/tmb_72173_2040.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz3)

        Glide.with(this@Dz3Activity)
            .load(url)
            .apply(RequestOptions().circleCrop())
            .into(dz3Image)
    }
}