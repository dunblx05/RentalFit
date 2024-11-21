package com.ssafy.rentalfit.util

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.ssafy.rentalfit.R

object Utils {

    // 다이얼로그 띄우기.
    fun showCustomDialog(context: Context, onConfirm: () -> Unit) {

        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog, null)

        val buttonConfirm = dialogView.findViewById<AppCompatButton>(R.id.buttonDialogConfirm)
        val buttonCancel = dialogView.findViewById<AppCompatButton>(R.id.buttonDialogCancel)

        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .create()

        // 다이얼로그 테두리 제거
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // 확인 버튼
        buttonConfirm.setOnClickListener {
            dialog.dismiss()
            showCustomToast(context, "예약이 완료되었습니다.")
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
}