package com.ssafy.rentalfit.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.databinding.ListHomeViewpagerItemBinding

class HomeViewPagerAdapter(private val homeBannerList: List<Int>) : RecyclerView.Adapter<HomeViewPagerAdapter.HomeViewPagerViewHolder>() {

    inner class HomeViewPagerViewHolder(private val binding: ListHomeViewpagerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            this.binding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        fun bindInfo(image: Int) {
            binding.apply {
                imageListHomeViewPagerItem.setImageResource(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewPagerViewHolder {
        val binding = ListHomeViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewPagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return homeBannerList.size
    }

    override fun onBindViewHolder(holder: HomeViewPagerViewHolder, position: Int) {
        holder.bindInfo(homeBannerList[position])
    }
}
