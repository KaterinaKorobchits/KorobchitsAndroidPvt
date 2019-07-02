package by.itacademy.korobchits.dz6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.korobchits.R

class Dz6StudentAdapter(
    private var items: List<Dz6Student>,
    private val listener: ClickListener
) : RecyclerView.Adapter<Dz6StudentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dz6StudentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dz6_student, parent, false)

        val holder = Dz6StudentHolder(view)
        holder.itemView.setOnClickListener() {
            listener.onStudentItemClick(items[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: Dz6StudentHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    interface ClickListener {
        fun onStudentItemClick(item: Dz6Student)
    }

    fun update(list: List<Dz6Student>) {
        items = list
        notifyDataSetChanged()
    }
}