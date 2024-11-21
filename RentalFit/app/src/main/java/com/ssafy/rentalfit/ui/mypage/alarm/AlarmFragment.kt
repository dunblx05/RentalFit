package com.ssafy.rentalfit.ui.mypage.alarm

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentAlarmBinding

/**
 * A simple [Fragment] subclass.
 * Use the [AlarmFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class AlarmFragment : BaseFragment<FragmentAlarmBinding>(
    FragmentAlarmBinding::bind,
    R.layout.fragment_alarm
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
        initEvent()
    }

    private fun initData() {
        binding.alarmList.adapter = AlarmAdapter()

        val dividerItemDecoration = DividerItemDecoration(activity, LinearLayoutManager.VERTICAL)

        // 색상 리소스를 Drawable로 변환
        val dividerDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.custom_divider)
        if (dividerDrawable != null) {
            dividerItemDecoration.setDrawable(dividerDrawable)
        }

        binding.alarmList.addItemDecoration(dividerItemDecoration)
    }

    private fun initEvent() {
        binding.alarmBackBtn.setOnClickListener {
            activity?.finish()
        }
    }

}