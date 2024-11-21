package com.ssafy.rentalfit.ui.equip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.databinding.ListEquipItemHorizontalBinding

class EquipHorizontalAdapter(private val list: List<Equip>): RecyclerView.Adapter<EquipHorizontalAdapter.EquipHorizontalViewHolder>() {

    lateinit var equipHorizontalListener: ItemClickListener

    interface ItemClickListener {
        fun onClick(equip: Equip)
    }

    inner class EquipHorizontalViewHolder(private val binding: ListEquipItemHorizontalBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindInfo(equip: Equip) {

            binding.apply {

                imageListEquipItemHorizontal.setImageResource(R.drawable.temp)
                textListEquipItemHorizontalName.text = equip.name
                textListEquipItemHorizontalPossible.text = "1"
                textListEquipItemHorizontalTotal.text = " / 3"

                root.setOnClickListener {
                    equipHorizontalListener.onClick(equip)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipHorizontalViewHolder {
        val binding = ListEquipItemHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EquipHorizontalViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: EquipHorizontalViewHolder, position: Int) {
        holder.bindInfo(list[position])
    }
}