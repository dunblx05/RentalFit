package com.ssafy.rentalfit.ui.mypage.history

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.ApplicationClass.Companion.SERVER_URL
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentPlaceHistoryBinding
import com.ssafy.rentalfit.util.Utils.formatDate
import com.ssafy.rentalfit.util.Utils.formatTime
import com.ssafy.rentalfit.util.Utils.makeComma

private const val TAG = "PlaceHistoryFragment_싸피"
class PlaceHistoryFragment : BaseFragment<FragmentPlaceHistoryBinding>(
    FragmentPlaceHistoryBinding::bind,
    R.layout.fragment_place_history
) {

    private val placeHistoryViewModel : PlaceHistoryViewModel by viewModels()
    private var placeId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        placeId = arguments?.getInt("placeId")!!
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: $placeId")
        registerObserver()
        placeHistoryViewModel.selectPlaceResByRid(placeId)
        initEvent()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun registerObserver() {
        placeHistoryViewModel.placeResDetail.observe(viewLifecycleOwner) {

            Glide.with(binding.placeHistoryDetailImg.context)
                .load("${SERVER_URL}images/${it.place.placeImg}")
                .into(binding.placeHistoryDetailImg)

            binding.placeHistoryDetailDate.text = formatDate(it.resStartTime)
            binding.placeHistoryDetailName.text = it.place.placeName
            binding.placeHistoryDetailPeople.text = "인원 : ${it.place.placePeople}명"
            binding.placeHistoryDetailStartDate.text = "시작 : ${formatTime(it.resStartTime)}"
            binding.placeHistoryDetailEndDate.text = "종료 : ${formatTime(it.resEndTime)}"
            binding.placeHistoryDetailCost.text = "금액 : ${makeComma(it.resCost)}"
        }
    }

    private fun initEvent() {
        binding.placeHistoryBackBtn.setOnClickListener {
            parentFragmentManager.popBackStack(
                "PlaceHistoryDetail",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }

        binding.placeHistoryDetailOkBtn.setOnClickListener {
            parentFragmentManager.popBackStack(
                "PlaceHistoryDetail",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }

    }
}