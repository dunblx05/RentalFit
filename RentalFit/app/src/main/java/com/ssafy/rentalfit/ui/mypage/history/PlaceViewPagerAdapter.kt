package com.ssafy.rentalfit.ui.mypage.history

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.rentalfit.base.ApplicationClass.Companion.SERVER_URL
import com.ssafy.rentalfit.data.model.response.PlaceReservationResponse
import com.ssafy.rentalfit.databinding.ListPlaceHistoryItemBinding
import com.ssafy.rentalfit.util.Utils.formatDate
import com.ssafy.rentalfit.util.Utils.makeComma

private const val TAG = "PlaceViewPagerAdapter_싸피"

class PlaceViewPagerAdapter(var placeList: List<PlaceReservationResponse>) :
    RecyclerView.Adapter<PlaceViewPagerAdapter.PlaceViewPagerViewHolder>() {
    inner class PlaceViewPagerViewHolder(private val binding: ListPlaceHistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(placeReservationResponse: PlaceReservationResponse) {

            binding.apply {

                // 나중에 ip주소 변경해줘야함
                Glide.with(placePagerImg.context)
                    .load("${SERVER_URL}images/${placeReservationResponse.place.placeImg}")
                    .into(placePagerImg)

                placePagerDate.text = "${formatDate(placeReservationResponse.resStartTime)}"
                placePagerName.text = placeReservationResponse.place.placeName
                placePagerPeople.text = "인원 : ${placeReservationResponse.place.placePeople}명"
                placePagerCost.text = "금액 : ${makeComma(placeReservationResponse.resCost)}"
            }

            binding.root.setOnClickListener {
                val position = layoutPosition
                myListener.onClick(placeReservationResponse.resId)
            }
        }
    }

    fun interface ItemClickListener {
        fun onClick(resId: Int)
    }

    lateinit var myListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewPagerViewHolder {
        val view =
            ListPlaceHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewPagerViewHolder(view)

    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PlaceViewPagerViewHolder, position: Int) {
        holder.bind(placeList[position])
    }
}