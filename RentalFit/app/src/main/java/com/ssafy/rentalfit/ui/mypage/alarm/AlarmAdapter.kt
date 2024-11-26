package com.ssafy.rentalfit.ui.mypage.alarm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.databinding.ListAlarmItemBinding
import com.ssafy.rentalfit.ui.mypage.history.EquipHistoryDetailAdapter

class AlarmAdapter(var alarmList: List<String>) : RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {
    inner class AlarmViewHolder(private val binding: ListAlarmItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindInfo(alarm: String) {

            binding.apply {
                alarmMsg.text = alarm
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val binding = ListAlarmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlarmViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return alarmList.size
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {

        holder.bindInfo(alarmList[position])
    }
}