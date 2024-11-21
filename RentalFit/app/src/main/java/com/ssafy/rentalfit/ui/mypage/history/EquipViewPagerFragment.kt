package com.ssafy.rentalfit.ui.mypage.history

import android.content.Context
import android.os.Bundle
import android.view.View
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MyPageActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentEquipViewPagerBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EquipViewPagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EquipViewPagerFragment : BaseFragment<FragmentEquipViewPagerBinding>(
    FragmentEquipViewPagerBinding::bind,
    R.layout.fragment_equip_view_pager
) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var equipViewPagerAdapter: EquipViewPagerAdapter

    private lateinit var activity : MyPageActivity

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
        equipViewPagerAdapter = EquipViewPagerAdapter()
        binding.placeEquipList.adapter = equipViewPagerAdapter
    }

    private fun initEvent() {
        equipViewPagerAdapter.myListener = object : EquipViewPagerAdapter.ItemClickListener {
            override fun onClick() {
                activity.changeFragmentMyPage("EquipHistoryDetail")
            }
        }
    }
}