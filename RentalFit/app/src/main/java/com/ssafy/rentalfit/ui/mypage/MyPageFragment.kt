package com.ssafy.rentalfit.ui.mypage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MainActivity
import com.ssafy.rentalfit.activity.MyPageActivity
import com.ssafy.rentalfit.base.ApplicationClass
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentMyPageBinding

class MyPageFragment: BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {

    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingToolbar()
        settingEvent()
    }

    // 툴바 설정
    private fun settingToolbar() {

        binding.apply {

            toolbarMyPage.apply {

                inflateMenu(R.menu.menu_toolbar_my_page)

                setOnMenuItemClickListener {

                    when(it.itemId) {

                        // 알림 화면
                        R.id.menu_my_page_alarm -> {

                            val intent = Intent(mainActivity, MyPageActivity::class.java)
                            intent.putExtra("name", "Alarm")
                            startActivity(intent)
                        }

                        // 로그아웃
                        R.id.menu_my_page_logout -> {

                            // Preference 지우기
                            ApplicationClass.sharedPreferencesUtil.deleteUser()
                            ApplicationClass.sharedPreferencesUtil.deleteUserCookie()

                            // 화면 이동
                            mainActivity.finish()
                        }
                    }

                    true
                }
            }
        }
    }

    // 이벤트 설정
    private fun settingEvent() {

        binding.apply {

            // 장소 예약 화면
            textMyPagePlaceHistory.setOnClickListener {

                val intent = Intent(mainActivity, MyPageActivity::class.java)
                intent.putExtra("name", "PlaceHistory")
                startActivity(intent)
            }

            // 장비 예약 화면
            textMyPageEquipHistory.setOnClickListener {

                val intent = Intent(mainActivity, MyPageActivity::class.java)
                intent.putExtra("name", "EquipHistory")
                startActivity(intent)
            }
        }
    }
}