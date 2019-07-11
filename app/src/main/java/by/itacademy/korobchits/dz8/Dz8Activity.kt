package by.itacademy.korobchits.dz8

import android.content.res.Configuration
import android.os.Bundle
import android.widget.FrameLayout
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.korobchits.R

class Dz8Activity : FragmentActivity(), Dz8StudentListFragment.Listener, Dz8StudentDetailsFragment.Listener,
    ListenerChangeStorage {

    private var isLandOrientation = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz8)

        isLandOrientation = (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)

        if (savedInstanceState == null && !isLandOrientation)
            completeContainer1Start()
        else {
            if (isLandOrientation && supportFragmentManager.findFragmentById(R.id.dz8Container1) is Dz8StudentListFragment) {
                supportFragmentManager.beginTransaction()
                    .remove(supportFragmentManager.findFragmentById(R.id.dz8Container1) as Dz8StudentListFragment)
                    .commit()
            } else if (!isLandOrientation && findViewById<FrameLayout>(R.id.dz8Container1).isEmpty())
                completeContainer1Start()
        }
    }

    private fun completeContainer1Start() {
        createTransaction(R.id.dz8Container1, Dz8StudentListFragment())
    }

    override fun onStorageChange() {
        supportFragmentManager.popBackStack()
        if (isLandOrientation)
            (supportFragmentManager.findFragmentById(R.id.dz8Container) as Dz8StudentListFragment).updateRecyclerList()
    }

    override fun onStudentItemClick(id: String) {
        createTransaction(R.id.dz8Container1, Dz8StudentDetailsFragment.getInstance(id))
    }

    override fun onAddButtonClick() {
        createTransaction(R.id.dz8Container1, Dz8StudentEditFragment.getInstance())
    }

    override fun onEditStudentClick(id: String) {
        supportFragmentManager.popBackStack()
        createTransaction(R.id.dz8Container1, Dz8StudentEditFragment.getInstance(id))
    }

    private fun createTransaction(idContainer: Int, fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(idContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}