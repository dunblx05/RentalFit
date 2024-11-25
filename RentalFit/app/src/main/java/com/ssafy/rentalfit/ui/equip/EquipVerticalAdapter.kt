package com.ssafy.rentalfit.ui.equip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.versionedparcelable.ParcelImpl
import com.ssafy.rentalfit.data.model.dto.Equip
import com.ssafy.rentalfit.databinding.ListEquipItemVerticalBinding

class EquipVerticalAdapter(private var categories: List<Pair<String, List<Equip>>>): RecyclerView.Adapter<EquipVerticalAdapter.EquipVerticalViewHolder>() {

    lateinit var equipVerticalListener: ItemClickListener

    interface ItemClickListener {
        fun onClick(equipId: Int)
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
                    override fun onClick(equipId: Int) {
                        equipVerticalListener.onClick(equipId)
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

    fun updateData(newEquipList: List<Equip>) {
        categories = newEquipList.groupBy { it.equipType }
            .map { Pair(it.key, it.value) }
        notifyDataSetChanged()
    }
}