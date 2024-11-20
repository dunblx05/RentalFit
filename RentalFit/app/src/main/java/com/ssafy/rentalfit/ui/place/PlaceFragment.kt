package com.ssafy.rentalfit.ui.place

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MainActivity
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentEquipBinding
import com.ssafy.rentalfit.databinding.FragmentPlaceBinding
import com.ssafy.rentalfit.ui.places.adapter.PlaceAdapter

class PlaceFragment : BaseFragment<FragmentPlaceBinding>(FragmentPlaceBinding::bind, R.layout.fragment_place)  {

    private lateinit var mainActivity: MainActivity
    private lateinit var placeAdapter: PlaceAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        placeAdapter = PlaceAdapter(getPlaceList())

        settingToolbar()
    }

    // 툴바 설정
    private fun settingToolbar() {

        binding.apply {

            toolbarPlace.apply {

                inflateMenu(R.menu.menu_toolbar_place)

                setOnMenuItemClickListener {

                    when(it.itemId) {

                        // 장바구니로 가기
                        R.id.menu_place_cart -> {

                            val intent = Intent(mainActivity, ReservationActivity::class.java)
                            intent.putExtra("name", "Cart")
                            startActivity(intent)
                        }

                    }

                    true
                }
            }
        }
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