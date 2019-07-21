package by.itacademy.korobchits.dz11.part1

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.korobchits.R
import by.itacademy.korobchits.dz8.ListenerChangeStorage

class Dz11Activity : FragmentActivity(), Dz11StudentListFragment.Listener, Dz11StudentDetailsFragment.Listener,
    ListenerChangeStorage {

    private var isLandOrientation = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz11)

        isLandOrientation = (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)

        if (savedInstanceState == null && !isLandOrientation)
            completeContainer1Start()
        else {
            if (isLandOrientation && supportFragmentManager.findFragmentById(R.id.dz8Container1) is Dz11StudentListFragment) {
                supportFragmentManager.beginTransaction()
                    .remove(supportFragmentManager.findFragmentById(R.id.dz8Container1) as Dz11StudentListFragment)
                    .commit()
            } else if (!isLandOrientation && supportFragmentManager.findFragmentById(R.id.dz8Container1) == null) {
                completeContainer1Start()
            }
        }
    }

    private fun completeContainer1Start() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.dz8Container1, Dz11StudentListFragment())
        transaction.commit()
    }

    override fun onStorageChange() {
        supportFragmentManager.popBackStack()
        if (isLandOrientation)
            (supportFragmentManager.findFragmentById(R.id.dz8Container) as Dz11StudentListFragment).updateRecyclerList()
    }

    override fun onStudentItemClick(id: String) {
        createTransaction(R.id.dz8Container1, Dz11StudentDetailsFragment.getInstance(id))
    }

    override fun onAddButtonClick() {
        createTransaction(R.id.dz8Container1, Dz11StudentEditFragment.getInstance())
    }

    override fun onEditStudentClick(id: String) {
        supportFragmentManager.popBackStack()
        createTransaction(R.id.dz8Container1, Dz11StudentEditFragment.getInstance(id))
    }

    private fun createTransaction(idContainer: Int, fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(idContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}