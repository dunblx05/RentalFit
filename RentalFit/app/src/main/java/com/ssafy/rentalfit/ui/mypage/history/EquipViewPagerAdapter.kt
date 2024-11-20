package com.ssafy.rentalfit.ui.mypage.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.R

class EquipViewPagerAdapter() : RecyclerView.Adapter<EquipViewPagerAdapter.EquipViewPagerViewHolder>() {
    inner class EquipViewPagerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_equip_history_item, parent, false)
        return EquipViewPagerViewHolder(view)

    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: EquipViewPagerViewHolder, position: Int) {

    }
}