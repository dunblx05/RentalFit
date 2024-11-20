package com.ssafy.rentalfit.ui.mypage.history

import android.os.Bundle
import android.view.View
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentPlaceViewPagerBinding

/**
 * A simple [Fragment] subclass.
 * Use the [PlaceViewPagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlaceViewPagerFragment : BaseFragment<FragmentPlaceViewPagerBinding>(
    FragmentPlaceViewPagerBinding::bind,
    R.layout.fragment_place_view_pager
) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        binding.placeHistoryList.adapter = PlaceViewPagerAdapter()
    }
}