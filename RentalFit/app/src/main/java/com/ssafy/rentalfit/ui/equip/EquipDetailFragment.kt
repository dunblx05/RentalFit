package com.ssafy.rentalfit.ui.equip

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentEquipDetailBinding

class EquipDetailFragment : BaseFragment<FragmentEquipDetailBinding>(FragmentEquipDetailBinding::bind, R.layout.fragment_equip_detail) {

    private lateinit var reservationActivity: ReservationActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        reservationActivity = context as ReservationActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingEvent()
    }


    // 이벤트 설정
    private fun settingEvent() {

        binding.apply {

            // 뒤로 가기
            imageEquipDetailBack.setOnClickListener {
                reservationActivity.finish()
            }

            // 장바구니로 가기.
            buttonEquipDetailOpenCart.setOnClickListener {

                reservationActivity.changeFragmentReservation("Cart", -1)
            }
        }
    }
}
