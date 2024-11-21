package com.ssafy.rentalfit.ui.place

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.databinding.FragmentReservationBottomSheetBinding
import com.ssafy.rentalfit.util.Utils

private const val TAG = "ReservationBottomSheetF_싸피"

class ReservationBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var reservationActivity: ReservationActivity
    private var _binding: FragmentReservationBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationBottomSheetBinding.inflate(inflater, container, false)
        reservationActivity = context as ReservationActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSchedule()
        // 버튼 클릭 시 동작 예시
        binding.buttonConfirm.setOnClickListener {
            Utils.showCustomDialog(reservationActivity) {
                reservationActivity.finish()

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun initSchedule() {
        // 먼저 기존 뷰들을 비웁니다.
        binding.firstRowTimeLabels.removeAllViews()
        binding.firstRowBlocks.removeAllViews()

        val distanceBetTime = 38
        val distanceBetBlock = 16

        // 9시 ~ 18시까지 시간과 블록을 두 줄에 나누어 추가
        for (hour in 9..18) {
            // 시간 라벨 추가
            val timeLabel = TextView(requireContext()).apply {
                text = hour.toString()
                textSize = 16f
                setTextColor(ContextCompat.getColor(requireContext(), R.color.oatmeal_main))
                setPadding(0, 0, distanceBetTime, 0)
            }

            // 시간 라벨을 첫 번째 줄에 추가
            if (hour <= 14) {
                binding.firstRowTimeLabels.addView(timeLabel)
            } else {
                // 두 번째 줄에 시간 라벨 추가
                binding.firstRowTimeLabels.addView(timeLabel)
            }

            // 해당 시간에 대한 블록 2개 추가 (30분 단위)
            repeat(2) {
                val block = View(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(30, 60).apply {
                        id = hour * 100 + it * 30 // 아이디는 900, 930......1700,1730
                        weight = 1f
                        marginStart = distanceBetBlock
                        marginEnd = 0
                    }
                    setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_main))
                }

                // 각 블록을 첫 번째 줄이나 두 번째 줄에 추가
                if (hour <= 14) {
                    binding.firstRowBlocks.addView(block)
                } else {
                    binding.firstRowBlocks.addView(block)
                }
            }
        }

        // 첫 번째 줄의 블록 개수와 맞추기 위해 보이지 않는 블록 추가
        val firstRowBlockCount = binding.firstRowBlocks.childCount
        if (firstRowBlockCount < binding.firstRowBlocks.childCount) {
            repeat(binding.firstRowBlocks.childCount - firstRowBlockCount) {
                val invisibleBlock = View(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(30, 60).apply {
                        weight = 1f
                        marginStart = distanceBetBlock
                        marginEnd = 0
                    }
                    visibility = View.INVISIBLE // 보이지 않는 블록
                }
                binding.firstRowBlocks.addView(invisibleBlock)
            }
        }

        // 일정 색상 변경 (예: 특정 시간에 대한 색상 변경)
        drawSchedule()
    }

    private fun drawSchedule() {
        // 첫 번째 줄의 블록 색상 변경 (예: 12:30 이전)
        for (item in binding.firstRowBlocks.children) {
            if (item.id <= 1230) {
                item.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.neon_main))
            }
        }
    }
}
