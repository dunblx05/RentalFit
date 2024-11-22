package com.ssafy.rentalfit.ui.place

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MainActivity
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.databinding.FragmentReservationBottomSheetBinding
import com.ssafy.rentalfit.util.Utils
import java.time.LocalTime
import java.time.format.DateTimeFormatter

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSchedule()

        setupTimePickers()
        binding.timePickerStart.minute=0
        binding.timePickerEnd.minute=0

        drawSchedule("09:30","12:30")
        drawSchedule("17:00","18:00")

        eraseBeforeCurrentTime(LocalTime.now())


        // 버튼 클릭 시 동작 예시
        binding.buttonConfirm.setOnClickListener {

            val startHour = binding.timePickerStart.hour
            val startMinute = binding.timePickerStart.minute
            val endHour = binding.timePickerEnd.hour
            val endMinute = binding.timePickerEnd.minute

            val startTime = LocalTime.of(startHour, startMinute)
            val endTime = LocalTime.of(endHour, endMinute)

            if (startTime.isAfter(endTime) || startTime == endTime) {
                Toast.makeText(
                    reservationActivity,
                    "시작 시간이 종료 시간보다 늦거나 같을 수 없습니다.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            Utils.showCustomDialog(reservationActivity) {
                val intent = Intent(reservationActivity, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                }
                startActivity(intent)
                reservationActivity.finish()
            }
        }

        binding.timePickerStart.setOnTimeChangedListener { view, hourOfDay, minute ->
            validateTime(view, hourOfDay, minute,true)
        }
        binding.timePickerEnd.setOnTimeChangedListener { view, hourOfDay, minute ->
            validateTime(view, hourOfDay, minute,false)
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
        for (hour in 9..17) {
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
                    setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.neon_main))
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

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun drawSchedule(startTime: String, endTime: String) {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val start = LocalTime.parse(startTime, formatter)
        val end = LocalTime.parse(endTime, formatter)

        // 첫 번째 줄의 블록 색상 변경
        for (item in binding.firstRowBlocks.children) {
            val itemTime = LocalTime.of(item.id / 100, item.id % 100) // id를 시간으로 변환 (예: 1230 -> 12:30)
            if (!itemTime.isBefore(start) && !itemTime.isAfter(end) && itemTime != end) {
                item.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_main))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun eraseBeforeCurrentTime(currentTime: LocalTime) {
        Log.d(TAG, "eraseBeforeCurrentTime: $currentTime")
        // 첫 번째 줄의 블록 초기화 (현재 시간 이전)
        for (item in binding.firstRowBlocks.children) {
            val itemTime = LocalTime.of(item.id / 100, item.id % 100) // id를 시간으로 변환 (예: 1230 -> 12:30)
            if (itemTime.isBefore(currentTime)) {
                item.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.darkgrey_main))
            }
            else{
                return
            }
        }
    }

    var startHour=0
    var startMinute=0
    var endHour=0
    var endMinute=0
    @RequiresApi(Build.VERSION_CODES.O)
    private fun validateTime(timePicker: TimePicker, hourOfDay: Int, minute: Int, isStart: Boolean) {
        var startTime = LocalTime.of(hourOfDay, minute)
        val minTime = LocalTime.of(9, 0)  // 최소 09:00
        val maxTime = LocalTime.of(18, 0) // 최대 18:00


        if (isStart){
            if(startMinute != minute){
                timePicker.hour = startHour
                startTime = LocalTime.of(startHour, minute)
            }
        }
        else{
            if(endMinute != minute){
                timePicker.hour = endHour
                startTime = LocalTime.of(endHour, minute)
            }
        }
        if (startTime.isBefore(minTime)) {
            Log.d(TAG, "startTime: ${startTime}, minTime: ${minTime}")
            Toast.makeText(requireContext(), "시간은 09:00에서 18:00 사이여야 합니다.", Toast.LENGTH_SHORT).show()
            timePicker.hour = minTime.hour
            timePicker.minute = minTime.minute
        }
        else if(startTime.isAfter(maxTime)){
            Toast.makeText(requireContext(), "시간은 09:00에서 18:00 사이여야 합니다.", Toast.LENGTH_SHORT).show()
            timePicker.hour = maxTime.hour
            timePicker.minute = maxTime.minute
        }
        if (isStart){
            startHour=timePicker.hour
            startMinute =timePicker.minute
        }
        else{
            endHour=timePicker.hour
            endMinute =timePicker.minute
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupTimePickers() {
        // TimePicker 초기 설정 및 시간 간격 적용
        binding.timePickerStart.apply {
            setTimeInterval(30)
            setOnTimeChangedListener { _, hour, minute ->
                validateTime(this, hour, minute, true)
            }
        }
        binding.timePickerEnd.apply {
            setTimeInterval(30)
            setOnTimeChangedListener { _, hour, minute ->
                validateTime(this, hour, minute, false)
            }
        }
    }

    private fun TimePicker.setTimeInterval(interval: Int) {
        try {
            val minutePicker = findViewById<NumberPicker>(
                resources.getIdentifier("minute", "id", "android")
            )
            val displayedValues = Array(60 / interval) { String.format("%02d", it * interval) }
            minutePicker.minValue = 0
            minutePicker.maxValue = displayedValues.size - 1
            minutePicker.displayedValues = displayedValues
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}
