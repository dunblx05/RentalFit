package com.ssafy.rentalfit.ui.mypage.history

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import com.google.android.material.tabs.TabLayoutMediator
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MyPageActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentHistoryBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment :
    BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::bind, R.layout.fragment_history) {

    private lateinit var activity: MyPageActivity

    private var selectedTab: String? = null

    private var param1: String? = null
    private var param2: String? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MyPageActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectedTab = arguments?.getString("selectedTab")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val historyViewPager = binding.historyViewPager
        val viewPagerAdapter = HistoryViewPagerAdapter(activity)

        // 뷰페이저 드래그 막기
        historyViewPager.isUserInputEnabled = false

        // 뷰페이저 어댑터 연결
        historyViewPager.adapter = viewPagerAdapter

        // 뷰페이저와 탭 레이아웃 연결
        TabLayoutMediator(binding.historyTabLayout, historyViewPager) { tab, position ->
            tab.text = if (position == 0) "장소 내역" else "장비 내역"
        }.attach()

        // 전달받은 탭 정보에 따라 초기 탭 설정.
        when (selectedTab) {

            // 장소 예약 내역 탭
            "PlaceHistory" -> {
                binding.historyViewPager.setCurrentItem(0, false)
            }

            // 장비 예약 내역 탭
            "EquipHistory" -> {
                binding.historyViewPager.setCurrentItem(1, false)
            }

            // 기본은 장소 예약 내역 탭으로 설정
            else -> {
                binding.historyViewPager.setCurrentItem(0, false)
            }
        }

        initEvent()
    }

    private fun initEvent() {
        binding.historyBackBtn.setOnClickListener {
            activity.finish()
        }
    }


}