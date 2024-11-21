package com.ssafy.rentalfit.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.R

class VerticalAdapter(private val categories: List<Pair<String, List<Place>>>) :
    RecyclerView.Adapter<VerticalAdapter.VerticalViewHolder>() {

    var onHorizontalItemClickListener: ((Place) -> Unit)? = null

    class VerticalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTitle: TextView = itemView.findViewById(R.id.verticalItemTitle)
        val horizontalRecyclerView: RecyclerView = itemView.findViewById(R.id.horizontalRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vertical, parent, false)
        return VerticalViewHolder(view)
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        val (categoryTitle, items) = categories[position]
        holder.categoryTitle.text = categoryTitle
        holder.horizontalRecyclerView.adapter = HorizontalAdapter(items)

        val horizontalAdapter = HorizontalAdapter(items)
        horizontalAdapter.onItemClickListener = { selectedItem ->
            onHorizontalItemClickListener?.invoke(selectedItem)
        }

        holder.horizontalRecyclerView.adapter = horizontalAdapter
    }

    override fun getItemCount(): Int = categories.size
}
