package com.ssafy.rentalfit.ui.mypage.alarm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.ui.mypage.history.EquipHistoryDetailAdapter

class AlarmAdapter : RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {
    inner class AlarmViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_alarm_item, parent, false)
        return AlarmViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
    }
}