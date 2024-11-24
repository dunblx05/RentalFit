package com.ssafy.rentalfit.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.rentalfit.base.ApplicationClass.Companion.SERVER_URL
import com.ssafy.rentalfit.data.model.dto.Equip
import com.ssafy.rentalfit.databinding.ListHomeEquipItemBinding

private const val TAG = "HomeEquipAdapter_싸피"
class HomeEquipAdapter(var equipList:List<Equip>): RecyclerView.Adapter<HomeEquipAdapter.HomeEquipViewHolder>() {

    lateinit var homeEquipListener: ItemClickListener

    interface ItemClickListener {
        fun onClick()
    }

    inner class HomeEquipViewHolder(private val binding: ListHomeEquipItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindInfo(equip: Equip) {

            binding.apply {

                Log.d(TAG, "bindInfo: ${equip.equipImg}")

                Glide.with(imageListHomeEquipItem.context)
                    .load("${SERVER_URL}images/${equip.equipImg}")
                    .into(imageListHomeEquipItem)

                textListHomeEquipItemName.text = equip.equipName

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
        return equipList.size
    }

    override fun onBindViewHolder(holder: HomeEquipViewHolder, position: Int) {
        holder.bindInfo(equipList[position])
    }
}