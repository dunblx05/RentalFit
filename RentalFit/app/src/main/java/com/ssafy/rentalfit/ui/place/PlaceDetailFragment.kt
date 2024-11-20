package com.ssafy.rentalfit.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentPlaceDetailBinding

class PlaceDetailFragment : BaseFragment<FragmentPlaceDetailBinding>(
    bind = { view -> FragmentPlaceDetailBinding.bind(view) },
    layoutResId = R.layout.fragment_place_detail
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 앱바 제목 설정
//        activity?.actionBar?.apply {
//            title = "장소 대여"
//        }

        // 예시로 장소 데이터 설정
        val placeImage = R.drawable.sample_place_image // 이미지 파일
        val placeLocation = "서울 강남구"
        val placeCapacity = "최대 100명"
        val placeDescription = "이 장소는 축구 경기를 위한 시설로, 넓은 운동장이 마련되어 있습니다."

        // 데이터를 뷰에 설정
        binding.apply {
            imageViewPlace.setImageResource(placeImage)
            textViewLocation.text = placeLocation
            textViewCapacity.text = placeCapacity
            textViewDescription.text = placeDescription
        }
    }
}
