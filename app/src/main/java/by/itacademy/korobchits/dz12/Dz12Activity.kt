package by.itacademy.korobchits.dz12

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.korobchits.R
import by.itacademy.korobchits.dz8.ListenerChangeStorage

class Dz12Activity : FragmentActivity(), Dz12StudentListFragment.Listener, Dz12StudentDetailsFragment.Listener,
    ListenerChangeStorage {

    private var isLandOrientation = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz12)

        isLandOrientation = (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)

        if (savedInstanceState == null && !isLandOrientation)
            completeContainer1Start()
        else {
            if (isLandOrientation && supportFragmentManager.findFragmentById(R.id.dz8Container1) is Dz12StudentListFragment) {
                supportFragmentManager.beginTransaction()
                    .remove(supportFragmentManager.findFragmentById(R.id.dz8Container1) as Dz12StudentListFragment)
                    .commit()
            } else if (!isLandOrientation && supportFragmentManager.findFragmentById(R.id.dz8Container1) == null) {
                completeContainer1Start()
            }
        }
    }

    private fun completeContainer1Start() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.dz8Container1, Dz12StudentListFragment())
        transaction.commit()
    }

    override fun onStorageChange() {
        while (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        }

        if (isLandOrientation)
            (supportFragmentManager.findFragmentById(R.id.dz8Container) as Dz12StudentListFragment).updateRecyclerList()
        else {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.dz8Container1, Dz12StudentListFragment())
            transaction.commit()
        }
    }

    override fun onStudentItemClick(id: String) {
        createTransaction(R.id.dz8Container1, Dz12StudentDetailsFragment.getInstance(id))
    }

    override fun onAddButtonClick() {
        createTransaction(R.id.dz8Container1, Dz12StudentEditFragment.getInstance())
    }

    override fun onEditStudentClick(id: String) {
        createTransaction(R.id.dz8Container1, Dz12StudentEditFragment.getInstance(id))
    }

    private fun createTransaction(idContainer: Int, fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(idContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}