package by.itacademy.korobchits.dz13

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import by.itacademy.korobchits.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class Dz13Activity : Activity() {

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz13)
    }

    override fun onStart() {
        super.onStart()
        val dz13TextView = findViewById<TextView>(R.id.dz13TextView)

        disposable = Observable
            .interval(1, TimeUnit.SECONDS)
            .filter { it % 2 == 0L }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                dz13TextView.text = it.toString()
            }, { throwable ->
                dz13TextView.text = throwable.toString()
            })
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }
}