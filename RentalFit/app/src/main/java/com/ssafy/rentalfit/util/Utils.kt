package com.ssafy.rentalfit.util

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import com.ssafy.rentalfit.R
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

object Utils {

    // 다이얼로그 띄우기.
    @SuppressLint("MissingInflatedId")
    fun showCustomDialog(context: Context, content: String, onConfirm: () -> Unit) {

        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog, null)

        val textViewContent = dialogView.findViewById<TextView>(R.id.textDialogContent)
        val buttonConfirm = dialogView.findViewById<AppCompatButton>(R.id.buttonDialogConfirm)
        val buttonCancel = dialogView.findViewById<AppCompatButton>(R.id.buttonDialogCancel)

        textViewContent.text = content

        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .create()

        // 다이얼로그 테두리 제거
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // 확인 버튼
        buttonConfirm.setOnClickListener {
            dialog.dismiss()
            onConfirm()
        }

        // 취소 버튼
        buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    // 토스트 띄우기.
    @SuppressLint("MissingInflatedId")
    fun showCustomToast(context: Context, message: String, iconResId: Int? = null) {

        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.toast, null)

        val content = layout.findViewById<TextView>(R.id.toast_message)
        val image = layout.findViewById<ImageView>(R.id.toast_icon)

        content.text = message

        if(iconResId != null) {
            image.setImageResource(iconResId)
        }
        else {
            image.visibility = View.GONE
        }

        val toast = Toast(context)

        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.setGravity(Gravity.BOTTOM, 0, 200)
        toast.show()
    }

    // 천단위 콤마
    fun makeComma(num: Int): String {
        val comma = DecimalFormat("#,###")
        return "${comma.format(num)} 원"
    }

    // 날짜 출력 형식 변경
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(input: String): String {
        // 입력 문자열을 LocalDateTime으로 변환
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val dateTime = LocalDateTime.parse(input, inputFormatter)

        // 원하는 출력 형식 지정
        val outputFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        return dateTime.format(outputFormatter)
    }

    // 날짜 -> 시간
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatTime(input: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val localDateTime = LocalDateTime.parse(input, formatter)
        return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun combineDateAndTime(date: Date, time: LocalTime): String {
        val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val localDateTime = LocalDateTime.of(localDate, time)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        return localDateTime.format(formatter)
    }

}