package com.ssafy.rentalfit.ui.place

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MainActivity
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentEquipBinding
import com.ssafy.rentalfit.databinding.FragmentPlaceBinding

private const val TAG = "PlaceFragment_싸피"

class PlaceFragment : BaseFragment<FragmentPlaceBinding>(FragmentPlaceBinding::bind, R.layout.fragment_place)  {

    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val verticalRecyclerView: RecyclerView = view.findViewById(R.id.verticalRecyclerView)

        // Sample data
        val sports = listOf("풋살", "야구", "축구", "수영", "탁구", "농구", "배구", "배드민턴")
        val sampleData = sports.map { sport ->
            sport to List(10) { "올드트래포드" to R.drawable.temp }
        }

        verticalRecyclerView.adapter = VerticalAdapter(sampleData)

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

}

data class Place(val name: String, val reservations: List<String>)