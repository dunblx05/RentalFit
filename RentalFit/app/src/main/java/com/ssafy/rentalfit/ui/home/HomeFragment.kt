package com.ssafy.rentalfit.ui.home

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MainActivity
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.base.ApplicationClass
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.data.remote.RetrofitUtil
import com.ssafy.rentalfit.databinding.FragmentHomeBinding
import com.ssafy.rentalfit.ui.equip.EquipViewModel
import com.ssafy.rentalfit.util.Utils
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

private const val TAG = "HomeFragment_싸피"
class HomeFragment: BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private lateinit var mainActivity: MainActivity

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val equipViewModel: EquipViewModel by viewModels()

    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter
    private lateinit var homePlaceAdapter: HomePlaceAdapter
    private lateinit var homeEquipAdapter: HomeEquipAdapter

    private var sliderJob: Job? = null

    // 다이얼로그 참조
    private var nfcDialog: AlertDialog? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()

        settingToolbar()
        settingViewPager()
        settingRecyclerViewPlace()
        settingRecyclerViewEquip()

        homeViewModel.selectEquip()
        homeViewModel.selectPlace()

        settingEvent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sliderJob?.cancel()
    }

    private fun registerObserver() {
        homeViewModel.equipList.observe(viewLifecycleOwner) {
            homeEquipAdapter.equipList = it
            homeEquipAdapter.notifyDataSetChanged()
        }

        homeViewModel.placeList.observe(viewLifecycleOwner) {
            homePlaceAdapter.placeList = it
            homePlaceAdapter.notifyDataSetChanged()
        }

        homeViewModel.nfcTagValue.observe(viewLifecycleOwner) { tagValue ->

            Log.d(TAG, "registerObserver: $tagValue")
            
            if (tagValue != null && tagValue != "") {

                nfcDialog?.dismiss()

                when (tagValue) {

                    "시작" -> {
                        Log.d(TAG, "registerObserver: $tagValue")
                        val token = ApplicationClass.sharedPreferencesUtil.getToken()
                        Log.d(TAG, "registerObserver: $token")
                        homeViewModel.startNfc(token)
                        homeViewModel.resetNfcTagValue()
                    }

                    "끝" -> {
                        Log.d(TAG, "registerObserver: $tagValue")
                        val token = ApplicationClass.sharedPreferencesUtil.getToken()
                        homeViewModel.finishNfc(token)
                        homeViewModel.resetNfcTagValue()
                    }

                    else -> {
                        Utils.showCustomToast(mainActivity, "알 수 없는 NFC 태그입니다.")
                    }
                }
            }
        }
    }

    // 툴바 설정
    private fun settingToolbar() {

        binding.apply {

            toolbarHome.apply {

                inflateMenu(R.menu.menu_toolbar_home)

                setOnMenuItemClickListener {

                    when (it.itemId) {

                        // NFC
                        R.id.menu_home_nfc -> {

                            val content = "NFC를 태깅해주세요."
                            val contentToast = "장소 대여가 시작 되었습니다."
                            nfcDialog = Utils.showCustomDialog(mainActivity, content, contentToast) {

                            }
                        }

                        // 장바구니
                        R.id.menu_home_cart -> {

                            val intent = Intent(mainActivity, ReservationActivity::class.java)
                            intent.putExtra("name", "Cart")
                            startActivity(intent)
                        }
                    }

                    true
                }
            }
        }
    }

    // 뷰페이저 설정
    private fun settingViewPager() {

        val images = listOf(
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3,
            R.drawable.banner4,
            R.drawable.banner5,
        )

        homeViewPagerAdapter = HomeViewPagerAdapter(images)

        binding.apply {

            viewpager2HomeBanner.adapter = homeViewPagerAdapter

            viewpager2HomeBanner.offscreenPageLimit = 1

            viewpager2HomeBanner.setPageTransformer { page: View, position: Float ->
                page.apply {
                    alpha = 0.5f + (1 - Math.abs(position))
                    scaleX = 1f
                    scaleY = 1f
                }
            }

            viewpager2HomeBanner.clipToPadding = true
            viewpager2HomeBanner.clipChildren = true

            // 인디케이터 연결
            indicatorHome.setViewPager(viewpager2HomeBanner)
            viewpager2HomeBanner.adapter?.registerAdapterDataObserver(indicatorHome.adapterDataObserver)

        }
    }

    // 장소 리사이클러뷰 설정
    private fun settingRecyclerViewPlace() {

        homePlaceAdapter = HomePlaceAdapter(emptyList())

        binding.apply {

            recyclerViewHomePlace.layoutManager =
                LinearLayoutManager(mainActivity, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewHomePlace.adapter = homePlaceAdapter
        }
    }

    // 장비 리사이클러뷰 설정
    private fun settingRecyclerViewEquip() {

        homeEquipAdapter = HomeEquipAdapter(emptyList())

        binding.apply {

            recyclerViewHomeEquip.layoutManager =
                LinearLayoutManager(mainActivity, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewHomeEquip.adapter = homeEquipAdapter
        }
    }

    // 이벤트 설정
    private fun settingEvent() {

        binding.apply {

            // 뷰페이저 자동 슬라이드 함수.
            autoSlideViewPager()

            // 장소 아이템 누른다면.
            homePlaceAdapter.homePlaceListener = object : HomePlaceAdapter.ItemClickListener {
                override fun onClick(placeId: Int) {

                    val intent = Intent(mainActivity, ReservationActivity::class.java)
                    intent.putExtra("name", "PlaceDetail")
                    intent.putExtra("placeId", placeId)
                    startActivity(intent)
                }
            }

            // 장비 아이템 누른다면.
            homeEquipAdapter.homeEquipListener = object : HomeEquipAdapter.ItemClickListener {
                override fun onClick(equipId: Int) {

                    val intent = Intent(mainActivity, ReservationActivity::class.java)
                    intent.putExtra("name", "EquipDetail")
                    intent.putExtra("equipId", equipId)
                    startActivity(intent)
                }
            }
        }
    }

    // 자동 슬라이드
    private fun autoSlideViewPager() {

        binding.apply {
            sliderJob = viewLifecycleOwner.lifecycleScope.launch {
                while (isActive) {
                    delay(3000)
                    if (homeViewPagerAdapter.itemCount > 0) {
                        val nextItem =
                            (viewpager2HomeBanner.currentItem + 1) % homeViewPagerAdapter.itemCount
                        viewpager2HomeBanner.setCurrentItem(nextItem, true)
                    }
                }
            }
        }
    }
}