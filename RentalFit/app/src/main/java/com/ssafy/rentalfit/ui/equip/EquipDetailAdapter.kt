package com.ssafy.rentalfit.ui.equip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.databinding.ListEquipDetailItemBinding

class EquipDetailAdapter(): RecyclerView.Adapter<EquipDetailAdapter.EquipDetailViewHolder>() {

    lateinit var equipDetailAddListener: ItemClickListener

    interface ItemClickListener {
        fun onClick()
    }

    inner class EquipDetailViewHolder(private val binding: ListEquipDetailItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindInfo(position: Int) {

            binding.apply {

                // 장바구니에 추가.
                addListEquipDetailItem.setOnClickListener {
                    equipDetailAddListener.onClick()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipDetailViewHolder {
        val binding = ListEquipDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EquipDetailViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: EquipDetailViewHolder, position: Int) {
        holder.bindInfo(position)
    }
}