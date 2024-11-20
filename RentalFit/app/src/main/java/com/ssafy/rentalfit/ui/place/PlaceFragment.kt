package com.ssafy.rentalfit.ui.place

import android.os.Bundle
import android.view.View
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentEquipBinding
import com.ssafy.rentalfit.databinding.FragmentPlaceBinding
import com.ssafy.rentalfit.ui.places.adapter.PlaceAdapter

class PlaceFragment : BaseFragment<FragmentPlaceBinding>(FragmentPlaceBinding::bind, R.layout.fragment_place)  {

    private lateinit var placeAdapter: PlaceAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        placeAdapter = PlaceAdapter(getPlaceList())
    }

    private fun getPlaceList(): List<Place> {
        // 실제 데이터를 받아오는 부분 (예시 데이터)
        return listOf(
            Place("축구장", listOf("축구장 1", "축구장 2", "축구장 3")),
            Place("야구장", listOf("야구장 1", "야구장 2", "야구장 3")),
            Place("농구장", listOf("농구장 1", "농구장 2", "농구장 3"))
        )
    }
}

data class Place(val name: String, val reservations: List<String>)