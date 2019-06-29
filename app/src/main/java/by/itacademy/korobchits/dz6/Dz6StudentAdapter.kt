package by.itacademy.korobchits.dz6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.korobchits.R

class Dz6StudentAdapter(private val items: MutableList<Dz6Student>) : RecyclerView.Adapter<Dz6StudentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dz6StudentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dz6_student, parent, false)
        return Dz6StudentHolder(view)
    }

    override fun onBindViewHolder(holder: Dz6StudentHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}