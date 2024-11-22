package com.ssafy.rentalfit.ui.place

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentPlaceDetailBinding

class PlaceDetailFragment : BaseFragment<FragmentPlaceDetailBinding>(
    bind = { view -> FragmentPlaceDetailBinding.bind(view) },
    layoutResId = R.layout.fragment_place_detail
) {

    private lateinit var reservationActivity: ReservationActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        reservationActivity = context as ReservationActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 앱바 제목 설정
//        activity?.actionBar?.apply {
//            title = "장소 대여"
//        }

        // 예시로 장소 데이터 설정
        val placeImage = R.drawable.temp // 이미지 파일
        val placeLocation = "창의관 지하 1층"
        val placeCapacity = "30명"
        val placeDescription = "XYZ 풋살장은 도심 속에서 손쉽게 풋살을 즐길 수 있는 최적의 공간입니다. 이곳은 최상급 인조잔디를 사용해 부상의 위험을 줄이고, 쾌적한 경기 환경을 제공합니다."

        // 데이터를 뷰에 설정
        binding.apply {
            imageViewPlace.setImageResource(placeImage)
            textViewLocation.text = placeLocation
            textViewCapacity.text = placeCapacity
            textViewDescription.text = placeDescription
        }

        settingEvent()
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
