package com.ssafy.rentalfit.ui.mypage.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.rentalfit.data.model.response.EquipOrderWithInfo
import com.ssafy.rentalfit.databinding.ListEquipHistoryDetailItemBinding
import com.ssafy.rentalfit.util.Utils.makeComma

class EquipHistoryDetailAdapter(var equipDetailList: List<EquipOrderWithInfo>) :
    RecyclerView.Adapter<EquipHistoryDetailAdapter.EquipHistoryDetailViewHolder>() {
    inner class EquipHistoryDetailViewHolder(private val binding: ListEquipHistoryDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(equipOrderWithInfo: EquipOrderWithInfo) {
            binding.apply {

                // ip주소 변경해줘야함
                Glide.with(equipHistoryDetailImg.context)
                    .load("http://192.168.0.8:8080/images/${equipOrderWithInfo.equip.equipImg}")
                    .into(equipHistoryDetailImg)

                equipHistoryDetailName.text = equipOrderWithInfo.equip.equipName
                equipHistoryDetailQuantity.text = "수량 : ${equipOrderWithInfo.quantity}개"
                equipHistoryDetailCost.text =
                    "금액 : ${makeComma(equipOrderWithInfo.equip.equipPrice)}"
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EquipHistoryDetailViewHolder {
        val view =
            ListEquipHistoryDetailItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return EquipHistoryDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return equipDetailList.size
    }

    override fun onBindViewHolder(holder: EquipHistoryDetailViewHolder, position: Int) {
        holder.bind(equipDetailList[position])
    }
}