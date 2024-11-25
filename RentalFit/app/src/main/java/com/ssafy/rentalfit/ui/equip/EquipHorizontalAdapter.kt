package com.ssafy.rentalfit.ui.equip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.ApplicationClass.Companion.SERVER_URL
import com.ssafy.rentalfit.data.model.dto.Equip
import com.ssafy.rentalfit.databinding.ListEquipItemHorizontalBinding

class EquipHorizontalAdapter(private val list: List<Equip>): RecyclerView.Adapter<EquipHorizontalAdapter.EquipHorizontalViewHolder>() {

    lateinit var equipHorizontalListener: ItemClickListener

    interface ItemClickListener {
        fun onClick(equipId: Int)
    }

    inner class EquipHorizontalViewHolder(private val binding: ListEquipItemHorizontalBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindInfo(equip: Equip) {

            binding.apply {

                Glide.with(imageListEquipItemHorizontal.context)
                    .load("${SERVER_URL}images/${equip.equipImg}")
                    .into(imageListEquipItemHorizontal)

                textListEquipItemHorizontalName.text = equip.equipName

                root.setOnClickListener {
                    equipHorizontalListener.onClick(equip.equipId)
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