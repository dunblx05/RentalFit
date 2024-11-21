package com.ssafy.rentalfit.ui.mypage.history

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MyPageActivity
import com.ssafy.rentalfit.databinding.ListPlaceHistoryItemBinding

class PlaceViewPagerAdapter(var activity: MyPageActivity) : RecyclerView.Adapter<PlaceViewPagerAdapter.PlaceViewPagerViewHolder>() {
    inner class PlaceViewPagerViewHolder(private val binding : ListPlaceHistoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewPagerViewHolder {
        val view = ListPlaceHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewPagerViewHolder(view)

    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: PlaceViewPagerViewHolder, position: Int) {
        holder.bind()
    }
}