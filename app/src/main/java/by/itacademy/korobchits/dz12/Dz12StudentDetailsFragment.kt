package by.itacademy.korobchits.dz12

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itacademy.korobchits.R
import by.itacademy.korobchits.dz6.Dz6Student
import by.itacademy.korobchits.dz8.ListenerChangeStorage
import by.itacademy.korobchits.utils.loadRoundImage

class Dz12StudentDetailsFragment : Fragment(), Dz12DetailsView {

    private lateinit var presenter: Dz12DetailsPresenter
    private var listener: Listener? = null
    private var listenerChangeStorage: ListenerChangeStorage? = null

    private lateinit var dz8ImageView: ImageView
    private lateinit var dz8NameTextView: TextView
    private lateinit var dz8AgeTextView: TextView
    private lateinit var dz12ProgressBar: ProgressBar
    private lateinit var dz12StudentDetails: ScrollView

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getInstance(idStudent: String): Dz12StudentDetailsFragment {
            val fragment = Dz12StudentDetailsFragment()
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
        val view = inflater.inflate(R.layout.fragment_dz12_student_details, container, false)

        val idStudent = arguments?.getString(ID_STUDENT, "")

        dz8ImageView = view.findViewById(R.id.dz8ImageView)
        dz8NameTextView = view.findViewById(R.id.dz8NameTextView)
        dz8AgeTextView = view.findViewById(R.id.dz8AgeTextView)
        dz12ProgressBar = view.findViewById(R.id.dz12ProgressBar)
        dz12StudentDetails = view.findViewById(R.id.dz12StudentDetails)
        val dz8DeleteStudentButton = view.findViewById<Button>(R.id.dz8DeleteStudentButton)
        val dz8EditStudentButton = view.findViewById<Button>(R.id.dz8EditStudentButton)

        if (idStudent == null)
            goBack()
        else {
            presenter = Dz12DetailsPresenter(idStudent)
            presenter.setView(this)
        }

        dz8DeleteStudentButton.setOnClickListener() {
            presenter.deleteStudent()
        }

        dz8EditStudentButton.setOnClickListener() {
            listener?.onEditStudentClick(idStudent!!)
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        listenerChangeStorage = null
        presenter.detach()
    }

    override fun show(user: Dz6Student) {
        dz12ProgressBar.visibility = View.GONE
        dz12StudentDetails.visibility = View.VISIBLE
        loadRoundImage(user.imageUrl, dz8ImageView)
        dz8NameTextView.text = user.name
        dz8AgeTextView.text = user.age.toString()
    }

    override fun showProgressBar() {
        dz12ProgressBar.visibility = View.VISIBLE
        dz12StudentDetails.visibility = View.GONE
    }

    override fun finish() {
        listenerChangeStorage?.onStorageChange()
    }

    override fun goBack() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    interface Listener {
        fun onEditStudentClick(id: String)
    }
}