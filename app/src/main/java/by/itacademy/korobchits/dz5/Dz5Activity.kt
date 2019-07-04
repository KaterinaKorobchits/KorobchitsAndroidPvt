package by.itacademy.korobchits.dz5

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import by.itacademy.korobchits.R
import kotlinx.android.synthetic.main.activity_dz5.*

class Dz5Activity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5)

        val editText = findViewById<EditText>(R.id.dz5EditText)
        val buttonEnter = findViewById<Button>(R.id.dz5ButtonEnter)
        val myDiagramView = findViewById<MyDiagramView>(R.id.MyDiagramView)
        editText.setOnEditorActionListener() { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    buttonEnter.visibility = View.VISIBLE
                    val inputManager: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
                    true
                }
                else -> false
            }
        }

        dz5ButtonEnter.setOnClickListener() {
            myDiagramView.arrayNumbers = editText.text.split(" ").map { it.toIntOrNull() }.filterNotNull()
            myDiagramView.visibility = View.VISIBLE
        }
    }
}