package com.ssafy.rentalfit.ui.mypage.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.R

class EquipHistoryDetailAdapter : RecyclerView.Adapter<EquipHistoryDetailAdapter.EquipHistoryDetailViewHolder>() {
    inner class EquipHistoryDetailViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EquipHistoryDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_equip_history_detail_item, parent, false)
        return EquipHistoryDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: EquipHistoryDetailViewHolder, position: Int) {

    }
}