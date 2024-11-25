package com.ssafy.rentalfit.ui.place

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.data.model.dto.Place
import com.ssafy.rentalfit.databinding.ListEquipItemVerticalBinding
import com.ssafy.rentalfit.databinding.ListPlaceItemVerticalBinding

class PlaceVerticalAdapter(private var categories: List<Pair<String, List<Place>>>) :
    RecyclerView.Adapter<PlaceVerticalAdapter.PlaceVerticalViewHolder>() {

    lateinit var placeVericalListener: ItemClickListener

    interface ItemClickListener{
        fun onClick(placeId:Int)
    }

    inner class PlaceVerticalViewHolder(private val binding: ListPlaceItemVerticalBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(item : Pair<String, List<Place>>) {

            val title = item.first
            val list = item.second

            binding.apply {
                verticalItemTitle.text = title
                val placHorizontalAdapter = PlaceHorizontalAdapter(list)

                horizontalRecyclerView.layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                horizontalRecyclerView.adapter = placHorizontalAdapter

                placHorizontalAdapter.placeHorizontalListener=
                    object : PlaceHorizontalAdapter.ItemClickListener {
                        override fun onClick(placeId: Int) {
                            placeVericalListener.onClick(placeId)
                        }
                    }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceVerticalViewHolder {
        val binding = ListPlaceItemVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceVerticalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceVerticalViewHolder, position: Int) {
        holder.bindInfo(categories[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newPlaceList: List<Place>) {
        categories = newPlaceList.groupBy { it.placeType }
            .map { Pair(it.key, it.value) }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = categories.size
}
