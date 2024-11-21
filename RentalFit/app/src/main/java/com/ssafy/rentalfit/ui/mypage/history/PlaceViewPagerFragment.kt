package com.ssafy.rentalfit.ui.mypage.history

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MyPageActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentPlaceViewPagerBinding
import com.ssafy.rentalfit.ui.place.Place

/**
 * A simple [Fragment] subclass.
 * Use the [PlaceViewPagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "PlaceViewPagerFragment_싸피"
class PlaceViewPagerFragment : BaseFragment<FragmentPlaceViewPagerBinding>(
    FragmentPlaceViewPagerBinding::bind,
    R.layout.fragment_place_view_pager
) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var placeViewPagerAdapter: PlaceViewPagerAdapter

    private lateinit var activity: MyPageActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MyPageActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initEvent()
    }

    private fun initData() {
        placeViewPagerAdapter = PlaceViewPagerAdapter(activity)
        binding.placeHistoryList.adapter = placeViewPagerAdapter
    }

    private fun initEvent() {
        placeViewPagerAdapter.myListener = object : PlaceViewPagerAdapter.ItemClickListener{
            override fun onClick() {
                Log.d(TAG, "onClick: ")
                activity.changeFragmentMyPage("PlaceHistoryDetail")
            }
        }
    }
}