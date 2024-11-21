package com.ssafy.rentalfit.ui.cart

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentCartBinding

class CartFragment: BaseFragment<FragmentCartBinding>(FragmentCartBinding::bind, R.layout.fragment_cart) {

    private lateinit var reservationActivity: ReservationActivity

    private lateinit var cartAdapter: CartAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        reservationActivity = context as ReservationActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingRecyclerView()
        settingEvent()
    }

    // 리사이클러뷰 설정
    private fun settingRecyclerView() {

        cartAdapter = CartAdapter()

        binding.apply {

            recyclerViewCart.layoutManager = LinearLayoutManager(reservationActivity, LinearLayoutManager.VERTICAL, false)
            recyclerViewCart.adapter = cartAdapter
        }
    }

    // 이벤트 설정
    private fun settingEvent() {

        binding.apply {

            // 삭제 하기.
            cartAdapter.cartDeleteListener = object : CartAdapter.ItemClickListener {
                override fun onClick() {

                }
            }

            // 수량 -
            cartAdapter.cartMinusListener = object : CartAdapter.ItemClickListener {
                override fun onClick() {

                }
            }

            // 수량 +
            cartAdapter.cartPlusListener = object : CartAdapter.ItemClickListener {
                override fun onClick() {

                }
            }

            // 예약 바텀시트 띄우기.
            buttonCartOpenBottomSheet.setOnClickListener {

            }
        }
    }
}