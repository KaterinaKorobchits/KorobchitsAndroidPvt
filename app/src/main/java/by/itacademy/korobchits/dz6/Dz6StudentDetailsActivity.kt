package by.itacademy.korobchits.dz6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import by.itacademy.korobchits.R
import by.itacademy.korobchits.utils.loadRoundImage
import kotlinx.android.synthetic.main.activity_dz6_student_details.*

class Dz6StudentDetailsActivity : Activity() {

    private var idStudent = 0L

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getIntent(context: Context, idStudent: Long): Intent {
            val intent = Intent(context, Dz6StudentDetailsActivity::class.java)
            intent.putExtra(ID_STUDENT, idStudent)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_details)

        idStudent = intent.getLongExtra(ID_STUDENT, 0)
        val user: Dz6Student? = Dz6StudentList.getInstance().listStudents.find { it.id == idStudent }

        user?.let {
            dz6NameTextView.text = user.name
            dz6AgeTextView.text = user.age.toString()
            loadRoundImage(user.imageUrl, dz6ImageView)
        }

        dz6DeleteStudentButton.setOnClickListener() {

            Dz6StudentList.getInstance().listStudents.remove(user)
            this.finish()
        }

        dz6EditStudentButton.setOnClickListener() {
            startActivity(Dz6StudentEditActivity.getIntent(this@Dz6StudentDetailsActivity, idStudent))
            this.finish()
        }
    }
}