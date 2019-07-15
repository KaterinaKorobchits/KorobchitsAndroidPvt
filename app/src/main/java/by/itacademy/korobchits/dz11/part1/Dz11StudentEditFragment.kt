package by.itacademy.korobchits.dz11.part1

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itacademy.korobchits.R
import by.itacademy.korobchits.dz6.Dz6Student
import by.itacademy.korobchits.dz8.Dz8StudentEditFragment
import by.itacademy.korobchits.dz8.ListenerChangeStorage

class Dz11StudentEditFragment : Fragment(), Dz11EditView {

    private lateinit var presenter: Dz11EditPresenter
    private val pattern = Patterns.WEB_URL
    private var listenerChangeStorage: ListenerChangeStorage? = null

    private lateinit var urlEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var nameEditText: EditText

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"
        private const val ID_STUDENT_NEW = "NEW_STUDENT"

        fun getInstance(idStudent: String = ID_STUDENT_NEW): Dz8StudentEditFragment {
            val fragment = Dz8StudentEditFragment()
            val args = Bundle()
            args.putString(ID_STUDENT, idStudent)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ListenerChangeStorage)
            listenerChangeStorage = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragmnet_dz8_student_edit, container, false)

        presenter = Dz11EditPresenter()
        presenter.setView(this)

        ageEditText = view.findViewById(R.id.dz8AgeEditText)
        nameEditText = view.findViewById(R.id.dz8NameEditText)
        urlEditText = view.findViewById(R.id.dz8UrlEditText)
        val saveStudentButton = view.findViewById<Button>(R.id.dz8SaveStudentButton)

        val idStudent = arguments?.getString(ID_STUDENT, "")

        if (idStudent != ID_STUDENT_NEW) {
            presenter.getStudentById(idStudent)
        }

        saveStudentButton.setOnClickListener() {
            val name = nameEditText.text.toString()
            val url = urlEditText.text.toString()
            val age = ageEditText.text.toString()

            if (idStudent != ID_STUDENT_NEW)
                presenter.saveStudent(url, name, age, idStudent!!)
            else
                presenter.saveStudent(url, name, age)
            listenerChangeStorage?.onStorageChange()
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        listenerChangeStorage = null
        presenter.detach()
    }

    override fun show(user: Dz6Student) {
        nameEditText.setText(user.name)
        ageEditText.setText(user.age.toString())
        urlEditText.setText(user.imageUrl)
    }

    override fun goBack() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun validateData(url: String, name: String, age: String): Boolean {
        if (!pattern.matcher(url).matches()) {
            Toast.makeText(context, "***Image URL: Not valid URL***", Toast.LENGTH_SHORT).show()
            return false
        } else if (name.isEmpty()) {
            Toast.makeText(context, "***Name: Must be filled in***", Toast.LENGTH_SHORT).show()
            return false
        } else if (age.isEmpty()) {
            Toast.makeText(context, "***Age: Must be filled in***", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}