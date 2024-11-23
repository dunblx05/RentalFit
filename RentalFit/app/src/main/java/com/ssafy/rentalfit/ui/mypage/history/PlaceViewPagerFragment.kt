package com.ssafy.rentalfit.ui.mypage.history

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MyPageActivity
import com.ssafy.rentalfit.base.ApplicationClass.Companion.sharedPreferencesUtil
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentPlaceViewPagerBinding

private const val TAG = "PlaceViewPagerFragment_싸피"

// 성현이한테 ID sharedPreference 저장하는거 만들어달라고 해야함
class PlaceViewPagerFragment : BaseFragment<FragmentPlaceViewPagerBinding>(
    FragmentPlaceViewPagerBinding::bind,
    R.layout.fragment_place_view_pager
) {
    private var placeViewPagerAdapter = PlaceViewPagerAdapter(emptyList())

    private lateinit var activity: MyPageActivity

    private val user = sharedPreferencesUtil.getUser()

    private val placeHistoryViewModel: PlaceHistoryViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MyPageActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObserver()

        placeHistoryViewModel.selectPlaceReservationByUid("kdy")

        binding.placeHistoryList.apply {
            adapter = placeViewPagerAdapter
        }

        initEvent()
    }

    private fun registerObserver() {
        placeHistoryViewModel.placeReservationList.observe(viewLifecycleOwner) {
            placeViewPagerAdapter.placeList = it
            placeViewPagerAdapter.notifyDataSetChanged()

        }
    }

//    private fun initData() {
//        placeViewPagerAdapter = PlaceViewPagerAdapter()
//        binding.placeHistoryList.adapter = placeViewPagerAdapter
//    }

    private fun initEvent() {
        placeViewPagerAdapter.myListener = object : PlaceViewPagerAdapter.ItemClickListener {
            override fun onClick(placeId: Int) {
                Log.d(TAG, "onClick: ")
                activity.changeFragmentMyPage("PlaceHistoryDetail", placeId)
            }
        }
    }
}