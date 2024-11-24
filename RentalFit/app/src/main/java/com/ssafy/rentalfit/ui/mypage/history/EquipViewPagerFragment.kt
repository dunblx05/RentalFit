package com.ssafy.rentalfit.ui.mypage.history

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MyPageActivity
import com.ssafy.rentalfit.base.ApplicationClass.Companion.sharedPreferencesUtil
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentEquipViewPagerBinding

class EquipViewPagerFragment : BaseFragment<FragmentEquipViewPagerBinding>(
    FragmentEquipViewPagerBinding::bind,
    R.layout.fragment_equip_view_pager
) {

    private lateinit var equipViewPagerAdapter: EquipViewPagerAdapter

    private lateinit var activity: MyPageActivity

    private val user = sharedPreferencesUtil.getUser()

    private val equipHistoryViewModel: EquipHistoryViewModel by viewModels()

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
        initData()
        equipHistoryViewModel.selectEquipOrderByUid(user.userId)
        initEvent()
    }

    private fun registerObserver() {
        equipHistoryViewModel.equipOrderList.observe(viewLifecycleOwner) {
            equipViewPagerAdapter.equipList = it
            equipViewPagerAdapter.notifyDataSetChanged()
        }
    }

    private fun initData() {
        equipViewPagerAdapter = EquipViewPagerAdapter(emptyList())
        binding.placeEquipList.adapter = equipViewPagerAdapter
    }

    private fun initEvent() {
        equipViewPagerAdapter.myListener = object : EquipViewPagerAdapter.ItemClickListener {
            override fun onClick(equipOrderId: Int) {
                activity.changeFragmentMyPage("EquipHistoryDetail", -1, equipOrderId)
            }
        }
    }
}