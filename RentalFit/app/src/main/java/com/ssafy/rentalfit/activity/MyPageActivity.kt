package com.ssafy.rentalfit.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.BaseActivity
import com.ssafy.rentalfit.databinding.ActivityMyPageBinding
import com.ssafy.rentalfit.ui.cart.CartFragment
import com.ssafy.rentalfit.ui.equip.EquipDetailFragment
import com.ssafy.rentalfit.ui.mypage.alarm.AlarmFragment
import com.ssafy.rentalfit.ui.mypage.history.HistoryFragment
import com.ssafy.rentalfit.ui.place.PlaceDetailFragment

class MyPageActivity : BaseActivity<ActivityMyPageBinding>(ActivityMyPageBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 기본으로 AlarmFragment가 열리도록 설정
        val name = intent.getStringExtra("name") ?: "Alarm"

        changeFragmentMyPage(name)
    }

    private fun changeFragmentMyPage(name: String) {

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
        }

        transaction.replace(R.id.myPageContainer, goto)
        transaction.commit()
    }
}