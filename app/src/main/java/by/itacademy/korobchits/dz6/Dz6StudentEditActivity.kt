package by.itacademy.korobchits.dz6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import by.itacademy.korobchits.R
import kotlinx.android.synthetic.main.activity_dz6_student_edit.*

class Dz6StudentEditActivity : Activity() {

    private var idStudent = 0L
    private var isNewStudent = false

    private val pattern = Patterns.WEB_URL

    private lateinit var ageEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var urlEditText: EditText

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getIntent(context: Context, idStudent: Long = System.currentTimeMillis()): Intent {
            val intent = Intent(context, Dz6StudentEditActivity::class.java)
            intent.putExtra(ID_STUDENT, idStudent)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_edit)

        ageEditText = findViewById(R.id.dz6AgeEditText)
        nameEditText = findViewById(R.id.dz6NameEditText)
        urlEditText = findViewById(R.id.dz6UrlEditText)

        idStudent = intent.getLongExtra(ID_STUDENT, 0)

        val oldStudent: Dz6Student? = Dz6StudentList.getInstance().listStudents.find { it.id == idStudent }
        oldStudent?.let {
            nameEditText.setText(it.name)
            ageEditText.setText(it.age.toString())
            urlEditText.setText(it.imageUrl)
        } ?: let { isNewStudent = true }

        dz6SaveStudentButton.setOnClickListener() {

            val name = nameEditText.text.toString()
            val url = urlEditText.text.toString()
            val age = if (ageEditText.text.toString() == "") null else ageEditText.text.toString().toInt()

            if (!pattern.matcher(url).matches())
                Toast.makeText(this, "***Image URL: Not valid URL***", Toast.LENGTH_SHORT).show()
            else if (age == null)
                Toast.makeText(this, "***Age: Must be filled in***", Toast.LENGTH_SHORT).show()
            else {
                when (isNewStudent) {
                    true -> Dz6StudentList.getInstance().listStudents.add(Dz6Student(idStudent, url, name, age))
                    false -> {
                        oldStudent?.apply { this.name = name; this.age = age; this.imageUrl = url }
                    }
                }
                this.finish()
            }
        }
    }
}