package com.ssafy.rentalfit.activity

import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.BaseActivity
import com.ssafy.rentalfit.databinding.ActivityMyPageBinding
import com.ssafy.rentalfit.ui.cart.CartFragment
import com.ssafy.rentalfit.ui.equip.EquipDetailFragment
import com.ssafy.rentalfit.ui.mypage.alarm.AlarmFragment
import com.ssafy.rentalfit.ui.mypage.history.EquipHistoryFragment
import com.ssafy.rentalfit.ui.mypage.history.HistoryFragment
import com.ssafy.rentalfit.ui.mypage.history.PlaceHistoryFragment
import com.ssafy.rentalfit.ui.place.PlaceDetailFragment

class MyPageActivity : BaseActivity<ActivityMyPageBinding>(ActivityMyPageBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 기본으로 AlarmFragment가 열리도록 설정
        val name = intent.getStringExtra("name") ?: "Alarm"

        onBackPressedDispatcher.addCallback(this) {
            val fragmentManager = supportFragmentManager
            if (fragmentManager.backStackEntryCount > 1) {
                fragmentManager.popBackStack() // 맨 위의 프래그먼트 제거
            } else {
                finish() // 프래그먼트가 없으면 액티비티 종료
            }
        }

        changeFragmentMyPage(name)
    }


    fun changeFragmentMyPage(name: String) {

        val transaction = supportFragmentManager.beginTransaction()

        var goto: Fragment = PlaceDetailFragment()

        when(name) {

            // 알림 화면
            "Alarm" -> {
                goto = AlarmFragment()
            }

            // 예약내역 화면 (장소)
            "PlaceHistory" -> {
                goto = HistoryFragment().apply {
                    arguments = Bundle().apply {
                        putString("selectedTab", "PlaceHistory")
                    }
                }
            }

            // 예약내역 화면 (장비)
            "EquipHistory" -> {
                goto = HistoryFragment().apply {
                    arguments = Bundle().apply {
                        putString("selectedTab", "EquipHistory")
                    }
                }
            }

            "PlaceHistoryDetail" -> {
                goto = PlaceHistoryFragment()
            }

            "EquipHistoryDetail" -> {
                goto = EquipHistoryFragment()
            }
        }

        transaction.replace(R.id.myPageContainer, goto)
            .addToBackStack(name)
        transaction.commit()
    }
}