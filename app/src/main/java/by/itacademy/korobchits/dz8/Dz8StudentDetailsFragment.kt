package by.itacademy.korobchits.dz8

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.itacademy.korobchits.R
import by.itacademy.korobchits.dz6.Dz6Student
import by.itacademy.korobchits.dz6.Dz6StudentsStorage
import by.itacademy.korobchits.utils.loadRoundImage

class Dz8StudentDetailsFragment : Fragment() {

    private var listener: Listener? = null
    private var listenerChangeStorage: ListenerChangeStorage? = null

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getInstance(idStudent: String): Dz8StudentDetailsFragment {
            val fragment = Dz8StudentDetailsFragment()
            val args = Bundle()
            args.putString(ID_STUDENT, idStudent)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener)
            listener = context
        if (context is ListenerChangeStorage)
            listenerChangeStorage = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_dz8_student_details, container, false)

        val idStudent = arguments?.getString(ID_STUDENT, "")
        val user: Dz6Student? = idStudent?.let { Dz6StudentsStorage.getStudentById(it) }

        if (user == null)
            activity?.supportFragmentManager?.popBackStack()
        else {
            loadRoundImage(user.imageUrl, view.findViewById(R.id.dz8ImageView))
            view.findViewById<TextView>(R.id.dz8NameTextView).text = user.name
            view.findViewById<TextView>(R.id.dz8AgeTextView).text = user.age.toString()

            view.findViewById<Button>(R.id.dz8DeleteStudentButton).setOnClickListener() {
                Dz6StudentsStorage.removeStudent(user)
                listenerChangeStorage?.onStorageChange()
            }

            view.findViewById<Button>(R.id.dz8EditStudentButton).setOnClickListener() {
                listener?.onEditStudentClick(user.id)
            }
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        listenerChangeStorage = null
    }

    interface Listener {
        fun onEditStudentClick(id: String)
    }
}