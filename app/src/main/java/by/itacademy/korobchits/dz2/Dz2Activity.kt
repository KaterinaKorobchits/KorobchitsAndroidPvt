package by.itacademy.korobchits.dz2

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import by.itacademy.korobchits.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dz2.*

class Dz2Activity : Activity() {

    private lateinit var url : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz2)

        val editText = findViewById<EditText>(R.id.dz2EditText)
        editText.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    url = editText.text.toString()
                    //url = "https://klike.net/uploads/posts/2018-07/1531483275_4.jpg"
                    dz2ButtonDownload.visibility = View.VISIBLE
                    false
                }
                else -> false
            }
        }

        dz2ButtonDownload.setOnClickListener() {
            dz2ProgressBar.visibility = View.VISIBLE
            Picasso.get()
                .load(url)
                .error(R.drawable.page_not_found)
                .transform(CircleTransform())
                .into(myImage, object : Callback {
                    override fun onSuccess() {
                        dz2ProgressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        dz2ProgressBar.visibility = View.GONE
                    }
                })
        }
    }
}