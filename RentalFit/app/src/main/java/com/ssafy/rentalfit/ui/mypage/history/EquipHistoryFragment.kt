package com.ssafy.rentalfit.ui.mypage.history

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MyPageActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentEquipHistoryBinding


class EquipHistoryFragment : BaseFragment<FragmentEquipHistoryBinding>(
    FragmentEquipHistoryBinding::bind,
    R.layout.fragment_equip_history
) {
    private lateinit var equipHistoryDetailAdapter: EquipHistoryDetailAdapter

    private lateinit var activity: MyPageActivity

    private val equipHistoryViewModel: EquipHistoryViewModel by viewModels()
    private var orderId = -1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MyPageActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderId = arguments?.getInt("equipOrderId")!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObserver()
        initData()
        equipHistoryViewModel.selectEquipOrderByOrderId(orderId)
        initEvent()
    }

    private fun registerObserver() {
        equipHistoryViewModel.equipOrderDetailList.observe(viewLifecycleOwner) {
            equipHistoryDetailAdapter.equipDetailList = it
            equipHistoryDetailAdapter.notifyDataSetChanged()
        }
    }

    private fun initData() {
        equipHistoryDetailAdapter = EquipHistoryDetailAdapter(emptyList())
        binding.equipHistoryDetailList.adapter = equipHistoryDetailAdapter

        val dividerItemDecoration = DividerItemDecoration(activity, LinearLayoutManager.VERTICAL)

        // 색상 리소스를 Drawable로 변환
        val dividerDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.custom_divider)
        if (dividerDrawable != null) {
            dividerItemDecoration.setDrawable(dividerDrawable)
        }

        binding.equipHistoryDetailList.addItemDecoration(dividerItemDecoration)
    }

    private fun initEvent() {
        binding.equipHistoryBackBtn.setOnClickListener {
            parentFragmentManager.popBackStack(
                "EquipHistoryDetail",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }

        binding.equipHistoryDetailOkBtn.setOnClickListener {
            parentFragmentManager.popBackStack(
                "EquipHistoryDetail",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )

        }

    }

}