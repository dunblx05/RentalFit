package com.ssafy.rentalfit.ui.mypage.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.databinding.ListEquipHistoryItemBinding
import com.ssafy.rentalfit.databinding.ListPlaceHistoryItemBinding

class EquipViewPagerAdapter() : RecyclerView.Adapter<EquipViewPagerAdapter.EquipViewPagerViewHolder>() {
    inner class EquipViewPagerViewHolder(private val binding : ListEquipHistoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.root.setOnClickListener {
                myListener.onClick()
            }
        }

    }

    fun interface ItemClickListener{
        fun onClick()
    }
    lateinit var myListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipViewPagerViewHolder {
        val view = ListEquipHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EquipViewPagerViewHolder(view)

    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: EquipViewPagerViewHolder, position: Int) {
        holder.bind()
    }
}