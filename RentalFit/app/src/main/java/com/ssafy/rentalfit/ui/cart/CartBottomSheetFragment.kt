package com.ssafy.rentalfit.ui.cart

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MainActivity
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.databinding.FragmentCartBottomSheetBinding
import com.ssafy.rentalfit.databinding.FragmentReservationBottomSheetBinding
import com.ssafy.rentalfit.util.Utils


class CartBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var reservationActivity: ReservationActivity
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
        binding.buttonCartBottomSheet.setOnClickListener {
            Utils.showCustomDialog(reservationActivity) {
                val intent = Intent(reservationActivity, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                }
                startActivity(intent)
                reservationActivity.finish()
            }
        }
    }
}