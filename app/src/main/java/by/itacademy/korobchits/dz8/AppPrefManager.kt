package by.itacademy.korobchits.dz8

import android.content.Context
import android.content.SharedPreferences

private const val SHARED_PREFS_NAME = "dfsfdsfsdfsdfsdf"
private const val TEXT_KEY = "dkfpeifhdg"

class AppPrefManager(context: Context) {

    private val sharePref: SharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    fun setUserText(text: String) {
        sharePref
            .edit()
            .putString(TEXT_KEY, text)
            .apply()
    }

    fun getUserText(): String {
        return sharePref.getString(TEXT_KEY, "") ?: ""
    }
}