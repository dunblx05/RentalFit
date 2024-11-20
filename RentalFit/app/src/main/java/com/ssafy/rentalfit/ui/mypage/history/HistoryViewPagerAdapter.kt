package com.ssafy.rentalfit.ui.mypage.history

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ssafy.rentalfit.activity.MyPageActivity

class HistoryViewPagerAdapter(activity: MyPageActivity) : FragmentStateAdapter(activity){
    val fragments : List<Fragment> = listOf(PlaceViewPagerFragment(), EquipViewPagerFragment())


    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}