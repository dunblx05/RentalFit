package com.ssafy.rentalfit.ui.place

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.base.ApplicationClass.Companion.SERVER_URL
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentPlaceDetailBinding

private const val TAG = "PlaceDetailFragment_싸피"
class PlaceDetailFragment : BaseFragment<FragmentPlaceDetailBinding>(
    bind = { view -> FragmentPlaceDetailBinding.bind(view) },
    layoutResId = R.layout.fragment_place_detail
) {

    private lateinit var reservationActivity: ReservationActivity

    private val placeDetailViewModel: PlaceDetailViewModel by viewModels()
    private var placeId = -1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        reservationActivity = context as ReservationActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        placeId = arguments?.getInt("placeId")!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: $placeId")
        registerObserver()
        placeDetailViewModel.selectPlaceDetail(placeId)
        settingEvent()
    }

    private fun registerObserver() {
        placeDetailViewModel.placeDetail.observe(viewLifecycleOwner) {

            Log.d(TAG, "registerObserver: $it")
            
            binding.apply {
                Glide.with(imageViewPlace.context)
                    .load("${SERVER_URL}images/${it.placeImg}")
                    .into(imageViewPlace)

                textDescription.text = it.placeName
                textViewLocation.text = it.placeLocation
                textViewCapacity.text = "${it.placePeople}명"
                textViewDescription.text = it.placeDetail
            }
        }
    }

    // 이벤트 설정
    private fun settingEvent() {

        binding.apply {

            // 뒤로 가기
            imagePlaceDetailBack.setOnClickListener {
                reservationActivity.finish()
            }

            // 바텀시트 띄우기
            buttonPlaceDetailReservation.setOnClickListener {
                val bottomSheet = ReservationBottomSheetFragment()
                bottomSheet.show(parentFragmentManager, "ReservationBottomSheet")
            }
        }
    }
}
