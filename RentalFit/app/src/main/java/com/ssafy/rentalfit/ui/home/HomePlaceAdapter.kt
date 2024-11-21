package com.ssafy.rentalfit.ui.home

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.databinding.ListHomePlaceItemBinding

class HomePlaceAdapter(): RecyclerView.Adapter<HomePlaceAdapter.HomePlaceViewHolder>() {

    lateinit var homePlaceListener: ItemClickListener

    interface ItemClickListener {
        fun onClick()
    }

    inner class HomePlaceViewHolder(private val binding: ListHomePlaceItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindInfo(position: Int) {

            binding.apply {

                // 아이템 하나를 클릭한다면.
                root.setOnClickListener {
                    homePlaceListener.onClick()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePlaceViewHolder {
        val binding = ListHomePlaceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomePlaceViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: HomePlaceViewHolder, position: Int) {
        holder.bindInfo(position)
    }
}