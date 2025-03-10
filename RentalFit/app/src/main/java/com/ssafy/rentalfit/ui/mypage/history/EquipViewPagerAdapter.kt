package com.ssafy.rentalfit.ui.mypage.history

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.rentalfit.base.ApplicationClass.Companion.SERVER_URL
import com.ssafy.rentalfit.data.model.response.EquipOrderResponse
import com.ssafy.rentalfit.databinding.ListEquipHistoryItemBinding
import com.ssafy.rentalfit.util.Utils.formatDate
import com.ssafy.rentalfit.util.Utils.makeComma

class EquipViewPagerAdapter(var equipList: List<EquipOrderResponse>) :
    RecyclerView.Adapter<EquipViewPagerAdapter.EquipViewPagerViewHolder>() {
    inner class EquipViewPagerViewHolder(private val binding: ListEquipHistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(equipOrderResponse: EquipOrderResponse) {

            binding.apply {

                // ip 주소 변경 필요함
                Glide.with(equipPagerImg.context)
                    .load("${SERVER_URL}images/${equipOrderResponse.details[0].equipImg}")
                    .into(equipPagerImg)

                equipPagerDate.text = "${formatDate(equipOrderResponse.equipOrderTime)}"

                if(equipOrderResponse.details.size == 1) {
                    equipPagerName.text =
                        "${equipOrderResponse.details[0].equipName}"
                }
                else {
                    equipPagerName.text =
                        "${equipOrderResponse.details[0].equipName} 외 ${equipOrderResponse.details.size - 1}건"
                }

                // 총 구입 수량 계산
                val totalQuantity = equipOrderResponse.details.sumOf { it.quantity }
                equipPagerQuantity.text = "수량 : ${totalQuantity}개"

                // 총 금액 계산
                val totalCost = equipOrderResponse.details.sumOf { it.equipPrice * it.quantity }
                equipPagerCost.text = "금액 : ${makeComma(totalCost)}"
            }

            binding.root.setOnClickListener {
                myListener.onClick(equipOrderResponse.equipOrderId)
            }
        }

    }

    fun interface ItemClickListener {
        fun onClick(equipOrderId: Int)
    }

    lateinit var myListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipViewPagerViewHolder {
        val view =
            ListEquipHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EquipViewPagerViewHolder(view)

    }

    override fun getItemCount(): Int {
        return equipList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: EquipViewPagerViewHolder, position: Int) {
        holder.bind(equipList[position])
    }
}