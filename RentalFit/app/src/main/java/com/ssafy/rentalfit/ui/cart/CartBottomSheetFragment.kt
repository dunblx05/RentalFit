package com.ssafy.rentalfit.ui.cart

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MainActivity
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.databinding.FragmentCartBottomSheetBinding
import com.ssafy.rentalfit.databinding.FragmentReservationBottomSheetBinding
import com.ssafy.rentalfit.ui.equip.EquipViewModel
import com.ssafy.rentalfit.util.ShoppingRepository.totalCnt
import com.ssafy.rentalfit.util.ShoppingRepository.totalPrice
import com.ssafy.rentalfit.util.Utils


class CartBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var reservationActivity: ReservationActivity
    private val equipViewModel: EquipViewModel by viewModels()

    private var _binding: FragmentCartBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBottomSheetBinding.inflate(inflater, container, false)
        reservationActivity = context as ReservationActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserve()
        settingEvent()
    }

    // ViewModel Observer 등록
    private fun registerObserve() {

        binding.apply {

            // 장바구니 총 수량
            equipViewModel.totalCnt.observe(viewLifecycleOwner) { totalCnt ->
                textViewQuantity.text = "${totalCnt}개"
            }

            // 장바구니 총 금액
            equipViewModel.totalPrice.observe(viewLifecycleOwner) { totalPrice ->
                textViewTotalPrice.text = "${Utils.makeComma(totalPrice)}"
            }
        }
    }

    // 이벤트 등록
    private fun settingEvent() {

        binding.apply {

            // 장비 구매 서버로 전송.
            buttonCartBottomSheet.setOnClickListener {

                Utils.showCustomDialog(reservationActivity) {

                    // 주문 서버로 전송.
                    equipViewModel.makeEquipOrder()
                    equipViewModel.clearShoppingList()

                    val intent = Intent(reservationActivity, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    }

                    startActivity(intent)

                    reservationActivity.finish()
                }
            }
        }
    }
}