package com.ssafy.rentalfit.ui.equip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.databinding.ListEquipItemVerticalBinding
import com.ssafy.rentalfit.ui.place.Place

class EquipVerticalAdapter(private val categories: List<Pair<String, List<Equip>>>): RecyclerView.Adapter<EquipVerticalAdapter.EquipVerticalViewHolder>() {

    lateinit var equipVerticalListener: ItemClickListener

    interface ItemClickListener {
        fun onClick(equip: Equip)
    }

    inner class EquipVerticalViewHolder(private val binding: ListEquipItemVerticalBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindInfo(item : Pair<String, List<Equip>>) {

            val title = item.first
            val list = item.second

            binding.apply {

                textListEquipItemVerticalTitle.text = title

                val equipHorizontalAdapter = EquipHorizontalAdapter(list)

                recyclerViewListEquipItemVerticalHorizontal.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewListEquipItemVerticalHorizontal.adapter = equipHorizontalAdapter

                equipHorizontalAdapter.equipHorizontalListener = object : EquipHorizontalAdapter.ItemClickListener {
                    override fun onClick(equip: Equip) {
                        equipVerticalListener.onClick(equip)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipVerticalViewHolder {
        val binding = ListEquipItemVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EquipVerticalViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: EquipVerticalViewHolder, position: Int) {
        holder.bindInfo(categories[position])
    }

}