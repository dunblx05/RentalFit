package com.ssafy.rentalfit.ui.mypage.history

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.R

class PlaceViewPagerAdapter() : RecyclerView.Adapter<PlaceViewPagerAdapter.PlaceViewPagerViewHolder>() {
    inner class PlaceViewPagerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_place_history_item, parent, false)
        return PlaceViewPagerViewHolder(view)

    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: PlaceViewPagerViewHolder, position: Int) {

    }
}