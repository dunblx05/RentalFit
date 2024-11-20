package com.ssafy.rentalfit.ui.mypage.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ssafy.rentalfit.R
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}