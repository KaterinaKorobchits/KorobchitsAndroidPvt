package by.itacademy.korobchits.dz6

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.korobchits.R

class Dz6StudentHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val dz6ImageView = itemView.findViewById<ImageView>(R.id.dz6ImageView)
    private val dz6TextView = itemView.findViewById<TextView>(R.id.dz6TextView)

    fun bind(student: Dz6Student) {
        dz6ImageView.setImageResource(R.drawable.ic_notifications_black_24dp)
        dz6TextView.text = student.name
    }
}