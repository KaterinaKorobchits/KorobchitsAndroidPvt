package by.itacademy.korobchits.dz9

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.korobchits.R
import by.itacademy.korobchits.dz9.entity.Poi

class Dz9PoiAdapter(
    private var items: List<Poi>,
    private val listener: ClickListener
) : RecyclerView.Adapter<Dz9PoiHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dz9PoiHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dz9, parent, false)

        val holder = Dz9PoiHolder(view)
        holder.itemView.setOnClickListener() {
            listener.onPoiItemClick(items[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: Dz9PoiHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    interface ClickListener {
        fun onPoiItemClick(item: Poi)
    }

    /*fun update(list: List<Dz6Student>) {
        items = list
        notifyDataSetChanged()
    }*/
}