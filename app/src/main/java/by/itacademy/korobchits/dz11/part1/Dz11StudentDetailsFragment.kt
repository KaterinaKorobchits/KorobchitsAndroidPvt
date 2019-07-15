package by.itacademy.korobchits.dz11.part1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.itacademy.korobchits.R
import by.itacademy.korobchits.dz6.Dz6Student
import by.itacademy.korobchits.dz8.ListenerChangeStorage
import by.itacademy.korobchits.utils.loadRoundImage

class Dz11StudentDetailsFragment : Fragment(), Dz11DetailsView {

    private lateinit var presenter: Dz11DetailsPresenter
    private var listener: Listener? = null
    private var listenerChangeStorage: ListenerChangeStorage? = null

    private lateinit var dz8ImageView: ImageView
    private lateinit var dz8NameTextView: TextView
    private lateinit var dz8AgeTextView: TextView

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getInstance(idStudent: String): Dz11StudentDetailsFragment {
            val fragment = Dz11StudentDetailsFragment()
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

        presenter = Dz11DetailsPresenter()
        presenter.setView(this)

        val idStudent = arguments?.getString(ID_STUDENT, "")

        dz8ImageView = view.findViewById(R.id.dz8ImageView)
        dz8NameTextView = view.findViewById(R.id.dz8NameTextView)
        dz8AgeTextView = view.findViewById(R.id.dz8AgeTextView)
        val dz8DeleteStudentButton = view.findViewById<Button>(R.id.dz8DeleteStudentButton)
        val dz8EditStudentButton = view.findViewById<Button>(R.id.dz8EditStudentButton)

        presenter.getStudentById(idStudent)

        dz8DeleteStudentButton.setOnClickListener() {
            presenter.deleteStudent()
            listenerChangeStorage?.onStorageChange()
        }

        dz8EditStudentButton.setOnClickListener() {
            listener?.onEditStudentClick(idStudent!!)
        }

        return view
    }

    override fun show(user: Dz6Student) {
        loadRoundImage(user.imageUrl, dz8ImageView)
        dz8NameTextView.text = user.name
        dz8AgeTextView.text = user.age.toString()
    }

    override fun goBack() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        listenerChangeStorage = null
        presenter.detach()
    }

    interface Listener {
        fun onEditStudentClick(id: String)
    }
}