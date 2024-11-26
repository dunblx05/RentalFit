package com.ssafy.rentalfit.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.rentalfit.base.ApplicationClass.Companion.SERVER_URL
import com.ssafy.rentalfit.data.model.dto.Place
import com.ssafy.rentalfit.databinding.ListHomePlaceItemBinding
import java.text.DecimalFormat

class HomePlaceAdapter(var placeList: List<Place>) :
    RecyclerView.Adapter<HomePlaceAdapter.HomePlaceViewHolder>() {

    lateinit var homePlaceListener: ItemClickListener

    interface ItemClickListener {
        fun onClick(placeId: Int)
    }

    inner class HomePlaceViewHolder(private val binding: ListHomePlaceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindInfo(place: Place) {
            binding.apply {

                Glide.with(imageListHomePlaceItem.context)
                    .load("${SERVER_URL}images/${place.placeImg}")
                    .into(imageListHomePlaceItem)

                val decimalFormat = DecimalFormat("#,###")
                val totalPriceText = "시간당 ₩ ${decimalFormat.format(place.placeCost*2)}\n(최대 인원: ${place.placePeople}명)"
                textListPlaceItemHorizontalCost.text = totalPriceText

                textListHomePlaceItemName.text = place.placeName

                // 아이템 하나를 클릭한다면.
                root.setOnClickListener {
                    homePlaceListener.onClick(place.placeId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePlaceViewHolder {
        val binding =
            ListHomePlaceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomePlaceViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    override fun onBindViewHolder(holder: HomePlaceViewHolder, position: Int) {
        holder.bindInfo(placeList[position])
    }
}