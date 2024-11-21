package com.ssafy.rentalfit.ui.mypage.history

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MyPageActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentEquipHistoryBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EquipHistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EquipHistoryFragment : BaseFragment<FragmentEquipHistoryBinding>(
    FragmentEquipHistoryBinding::bind,
    R.layout.fragment_equip_history
) {

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
        binding.equipHistoryDetailList.adapter = EquipHistoryDetailAdapter()

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