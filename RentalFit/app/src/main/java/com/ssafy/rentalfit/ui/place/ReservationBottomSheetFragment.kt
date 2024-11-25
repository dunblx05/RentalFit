package com.ssafy.rentalfit.ui.place

import android.content.Intent
import android.graphics.drawable.ColorDrawable
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
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MainActivity
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.base.ApplicationClass
import com.ssafy.rentalfit.data.model.dto.Place
import com.ssafy.rentalfit.data.model.response.PlaceReservationResponse
import com.ssafy.rentalfit.data.remote.RetrofitUtil.Companion.homeService
import com.ssafy.rentalfit.data.remote.RetrofitUtil.Companion.placeReservationService
import com.ssafy.rentalfit.databinding.FragmentReservationBottomSheetBinding
import com.ssafy.rentalfit.util.Utils.combineDateAndTime
import com.ssafy.rentalfit.util.Utils.formatTime
import com.ssafy.rentalfit.util.Utils.showCustomDialog
import com.ssafy.rentalfit.util.Utils.showCustomToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

private const val TAG = "ReservationBottomSheetF_싸피"

class ReservationBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var reservationActivity: ReservationActivity
    private var _binding: FragmentReservationBottomSheetBinding? = null
    private val binding get() = _binding!!
    private var placeId: Int = -1
    lateinit var place: Place
    lateinit var focusDate :Date
    val defaultStartTime = 10
    val defaultEndTime = 17

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationBottomSheetBinding.inflate(inflater, container, false)
        reservationActivity = context as ReservationActivity
        arguments?.let {
            placeId = it.getInt("placeId", -1)
        }
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            runCatching {
                place = homeService.selectPlaceById(placeId)
            }.onSuccess {
                focusDate = Date()
                initSheet()
                settingEvent()
            }.onFailure {
                Log.d(TAG, "onViewCreated: Place is null")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun settingEvent(){
        // 버튼 클릭 시 동작 예시
        binding.buttonConfirm.setOnClickListener {

            val startHour = binding.timePickerStart.hour
            val startMinute  = if(binding.timePickerStart.minute == 1) {
                30
            }else{
                0
            }
            val endHour = binding.timePickerEnd.hour
            val endMinute = if(binding.timePickerEnd.minute == 1) {
                30
            }else{
                0
            }

            val startTime = LocalTime.of(startHour, startMinute)
            val endTime = LocalTime.of(endHour, endMinute)

            if (startTime.isAfter(endTime) || startTime == endTime) {
                showCustomToast(
                    reservationActivity,
                    "시작 시간이 종료 시간보다 늦거나 같을 수 없습니다.",
                )

                return@setOnClickListener
            }
            when(isAvailableReserve(startTime, endTime)){
                1 ->{
                    showCustomToast(
                        reservationActivity,
                        "해당 시간대에 다른 예약이 들어있습니다.",
                    )
                    return@setOnClickListener
                }
                2->{
                    showCustomToast(
                        reservationActivity,
                        "이미 지나간 시간대입니다.",
                    )
                    return@setOnClickListener
                }
            }



            val content =
                (SimpleDateFormat("yyyy년 MM월 dd일 ${place.placeLocation} ${place.placeName}")).format(focusDate)+"\n"+
                if(startTime.minute == 0) {
                    "${startTime.hour}시부터 "
                }else {
                    "${startTime.hour}시 ${startTime.minute}분부터 "
                }+
                if(endTime.minute == 0) {
                    "${endTime.hour}시까지 예약을 진행합니다. \n"
                }else {
                    " ${endTime.hour}시 ${endTime.minute}분까지 예약을 진행합니다. \n"
                }+
                        "예약 비용은 ${totalPrice} 원입니다. \n"+
                        "예약을 완료하시면 취소가 불가능합니다. \n 예약하시겠습니까?"


            showCustomDialog(reservationActivity, content) {
                val intent = Intent(reservationActivity, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                }


                CoroutineScope(Dispatchers.Main).launch {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val formattedDate = dateFormat.format(focusDate)

                    val reservations = placeReservationService.selectResByPidInDate(placeId,
                        formattedDate
                    )
                    reservations.forEach { reservation ->
//                    Log.d(TAG, "bindInfo: ${reservation.resStartTime}, ${reservation.resEndTime}")
                        drawSchedule(
                            formatTime(reservation.resStartTime),
                            formatTime(reservation.resEndTime)
                        )
                    }
                    if(isAvailableReserve(startTime, endTime) != 0){
                        showCustomToast(requireContext(), "예약이 찼습니다. 다시 예약해주세요")
                        return@launch
                    }
                    else{
                        var placeReservationResponse: PlaceReservationResponse = PlaceReservationResponse(
                            -1, userId = ApplicationClass.sharedPreferencesUtil.getUser().userId,
                            placeId = placeId, resStartTime =  combineDateAndTime(focusDate, startTime),
                            resEndTime = combineDateAndTime(focusDate, endTime),
                            resCost = totalPrice, place = place
                        )
                        placeReservationService.insertPlaceReservation(placeReservationResponse)
                        showCustomToast(requireContext(), "예약이 완료되었습니다.")
                        startActivity(intent)
                        reservationActivity.finish()
                    }
                }
            }
        }

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth) // month는 0부터 시작하므로 그대로 사용

            focusDate = calendar.time
            initSheet()
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initSheet(){
        initSchedule()
        setupTimePickers()
        drawAllSchedule()
        eraseBeforeCurrentTime()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initSchedule() {
        // 먼저 기존 뷰들을 비웁니다.
        binding.firstRowTimeLabels.removeAllViews()
        binding.firstRowBlocks.removeAllViews()
        binding.timePickerStart.apply {
            hour=defaultStartTime
            minute=0
        }
        binding.timePickerEnd.apply {
            hour=defaultEndTime
            minute=0
        }
        validateTime(binding.timePickerStart, defaultStartTime,0,true)
        validateTime(binding.timePickerEnd, defaultEndTime,0,false)

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
    private fun drawAllSchedule(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = dateFormat.format(focusDate)

                Log.d(TAG, "drawAllSchedule: $formattedDate")
                val reservations = placeReservationService.selectResByPidInDate(placeId,
                    formattedDate
                )
                reservations.forEach { reservation ->
//                    Log.d(TAG, "bindInfo: ${reservation.resStartTime}, ${reservation.resEndTime}")
                    drawSchedule(
                        formatTime(reservation.resStartTime),
                        formatTime(reservation.resEndTime)
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace() // 예외 처리
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun drawSchedule(startTime: String, endTime: String) {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val start = LocalTime.parse(startTime, formatter)
        val end = LocalTime.parse(endTime, formatter)

        // 첫 번째 줄의 블록 색상 변경
        val calendarToday = Calendar.getInstance().apply {
            time = Date() // 오늘 날짜
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val calendarInputDate = Calendar.getInstance().apply {
            time = focusDate // 입력받은 날짜
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val time = when {
            calendarInputDate.after(calendarToday) -> LocalTime.of(0, 0)
            calendarInputDate.before(calendarToday) -> LocalTime.of(23, 59)
            else -> LocalTime.now()
        }
        for (item in binding.firstRowBlocks.children) {
            val itemTime = LocalTime.of(item.id / 100, item.id % 100) // id를 시간으로 변환 (예: 1230 -> 12:30)
            if (!itemTime.isBefore(start) && !itemTime.isAfter(end) && itemTime != end) {
                if (calendarInputDate.before(calendarToday)){
                    item.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.darkgrey_main))
                }
                else if(calendarInputDate.equals(calendarToday) && itemTime.isBefore(time)){
                    item.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.darkgrey_main))
                }
                else{
                    item.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_main))
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun eraseBeforeCurrentTime() {
        val calendarToday = Calendar.getInstance().apply {
            time = Date() // 오늘 날짜
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val calendarInputDate = Calendar.getInstance().apply {
            time = focusDate // 입력받은 날짜
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val time = when {
            calendarInputDate.after(calendarToday) -> LocalTime.of(0, 0)
            calendarInputDate.before(calendarToday) -> LocalTime.of(23, 59)
            else -> LocalTime.now()
        }


        for (item in binding.firstRowBlocks.children) {
            val itemTime = LocalTime.of(item.id / 100, item.id % 100) // id를 시간으로 변환 (예: 1230 -> 12:30)
            if (itemTime.isBefore(time)) {
                item.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.darkneon_main))
            }
            else{
                break
            }
        }
    }

    var startHour=defaultStartTime
    var startMinute=0
    var endHour=defaultEndTime
    var endMinute=0
    var totalPrice=0
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
//            showCustomToast(
//                reservationActivity,
//                "시간은 09:00에서 18:00 사이여야 합니다.",
//            )
            timePicker.hour = minTime.hour
            timePicker.minute = minTime.minute
        }
        else if(startTime.isAfter(maxTime)){
//            showCustomToast(
//                reservationActivity,
//                "시간은 09:00에서 18:00 사이여야 합니다.",
//            )
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

        var startInt = startHour * 2 + (startMinute)
        var endInt = endHour * 2 + (endMinute)
        totalPrice = (endInt - startInt) * place.placeCost
        if(totalPrice >= 0){
            val decimalFormat = DecimalFormat("#,###")
            val totalPriceText = "₩ ${decimalFormat.format(totalPrice)}"
            binding.textViewTotalPrice.text = totalPriceText
        }
        else{
            binding.textViewTotalPrice.text = "₩ ???"
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isAvailableReserve(startTime: LocalTime, endTime: LocalTime) : Int{

        for (item in binding.firstRowBlocks.children) {
            val itemTime = LocalTime.of(item.id / 100, item.id % 100) // id를 시간으로 변환 (예: 1230 -> 12:30)
            if (!itemTime.isBefore(startTime) && !itemTime.isAfter(endTime) && itemTime != endTime) {
                val backgroundColor = (item.background as? ColorDrawable)?.color
                val passedNeonColor = ContextCompat.getColor(item.context, R.color.darkneon_main)
                val passedGreyColor = ContextCompat.getColor(item.context, R.color.darkgrey_main)
                val greyColor = ContextCompat.getColor(item.context, R.color.grey_main)
                if (backgroundColor != null) {
                    if(backgroundColor.equals(greyColor)){
                        return 1
                    }
                    else if(backgroundColor.equals(passedGreyColor)){
                        return 2
                    }
                    else if(backgroundColor.equals(passedNeonColor)){
                        return 2
                    }
                }
            }
            else if(itemTime.isAfter(endTime)){
                return 0
            }
        }

        return 0
    }
}
