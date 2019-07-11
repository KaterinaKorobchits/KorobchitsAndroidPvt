package by.itacademy.korobchits.dz8

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
import by.itacademy.korobchits.dz6.Dz6StudentsStorage

class Dz8StudentEditFragment : Fragment() {

    private val pattern = Patterns.WEB_URL
    private var listenerChangeStorage: ListenerChangeStorage? = null

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

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is ListenerChangeStorage)
            listenerChangeStorage = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragmnet_dz8_student_edit, container, false)

        val ageEditText = view.findViewById<EditText>(R.id.dz8AgeEditText)
        val nameEditText = view.findViewById<EditText>(R.id.dz8NameEditText)
        val urlEditText = view.findViewById<EditText>(R.id.dz8UrlEditText)

        val idStudent = arguments?.getString(ID_STUDENT, "")

        if (idStudent != ID_STUDENT_NEW) {
            val oldStudent = idStudent?.let { Dz6StudentsStorage.getStudentById(it) }
            if (oldStudent == null)
                activity?.supportFragmentManager?.popBackStack()
            else {
                nameEditText.setText(oldStudent.name)
                ageEditText.setText(oldStudent.age.toString())
                urlEditText.setText(oldStudent.imageUrl)
            }
        }

        view.findViewById<Button>(R.id.dz8SaveStudentButton).setOnClickListener() {

            val name = nameEditText.text.toString()
            val url = urlEditText.text.toString()
            val age = if (ageEditText.text.toString() == "") null else ageEditText.text.toString().toInt()

            if (!pattern.matcher(url).matches())
                Toast.makeText(context, "***Image URL: Not valid URL***", Toast.LENGTH_SHORT).show()
            else if (name.isEmpty())
                Toast.makeText(context, "***Name: Must be filled in***", Toast.LENGTH_SHORT).show()
            else if (age == null)
                Toast.makeText(context, "***Age: Must be filled in***", Toast.LENGTH_SHORT).show()
            else {
                when (idStudent) {
                    ID_STUDENT_NEW -> Dz6StudentsStorage.addStudent(
                        Dz6Student(System.currentTimeMillis().toString(), url, name, age)
                    )
                    else -> Dz6StudentsStorage.addStudent(Dz6Student(idStudent!!, url, name, age))
                }
                listenerChangeStorage?.onStorageChange()
            }
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        listenerChangeStorage = null
    }
}