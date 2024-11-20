package com.ssafy.rentalfit.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.BaseActivity
import com.ssafy.rentalfit.databinding.ActivityReservationBinding
import com.ssafy.rentalfit.ui.place.PlaceDetailFragment

class ReservationActivity : BaseActivity<ActivityReservationBinding>(ActivityReservationBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        // 여기에 액티비티 초기화 및 UI 작업 추가
    }

    fun initView(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, PlaceDetailFragment()) // fragmentContainer는 FrameLayout의 id
            .commit()
    }
}
