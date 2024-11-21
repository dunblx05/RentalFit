package com.ssafy.rentalfit.ui.mypage.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentPlaceHistoryBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlaceHistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlaceHistoryFragment : BaseFragment<FragmentPlaceHistoryBinding>(
    FragmentPlaceHistoryBinding::bind,
    R.layout.fragment_place_history
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvent()
    }

    private fun initEvent() {
        binding.placeHistoryBackBtn.setOnClickListener {
            parentFragmentManager.popBackStack("PlaceHistoryDetail", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        binding.placeHistoryDetailOkBtn.setOnClickListener {
            parentFragmentManager.popBackStack("PlaceHistoryDetail", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

    }
}