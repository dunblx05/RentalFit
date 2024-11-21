package com.ssafy.rentalfit.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.databinding.ListHomeEquipItemBinding

class HomeEquipAdapter(): RecyclerView.Adapter<HomeEquipAdapter.HomeEquipViewHolder>() {

    lateinit var homeEquipListener: ItemClickListener

    interface ItemClickListener {
        fun onClick()
    }

    inner class HomeEquipViewHolder(private val binding: ListHomeEquipItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindInfo(position: Int) {

            binding.apply {

                // 아이템 하나를 클릭한다면.
                root.setOnClickListener {
                    homeEquipListener.onClick()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeEquipViewHolder {
        val binding = ListHomeEquipItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeEquipViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: HomeEquipViewHolder, position: Int) {
        holder.bindInfo(position)
    }
}