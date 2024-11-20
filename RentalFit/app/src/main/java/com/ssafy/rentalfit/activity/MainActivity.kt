package com.ssafy.rentalfit.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.BaseActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.ActivityMainBinding
import com.ssafy.rentalfit.ui.equip.EquipFragment
import com.ssafy.rentalfit.ui.home.HomeFragment
import com.ssafy.rentalfit.ui.mypage.MyPageFragment
import com.ssafy.rentalfit.ui.place.PlaceFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // HomeFragment 처음에 표시
        if(savedInstanceState == null) {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.containerMain, HomeFragment())
                .commit()
        }

        settingBottomNavigation()
    }

    // 바텀 네비게이션 설정
    private fun settingBottomNavigation() {

        binding.apply {

            bottomNaviMain.setOnItemSelectedListener {

                when(it.itemId) {

                    // 홈
                    R.id.menu_bottom_home -> {
                        changeFragment("Home")
                    }

                    // 장소
                    R.id.menu_bottom_place -> {
                        changeFragment("Place")
                    }

                    // 장비
                    R.id.menu_bottom_equip -> {
                        changeFragment("Equip")
                    }

                    // 마이페이지
                    R.id.menu_bottom_mypage -> {
                        changeFragment("MyPage")
                    }
                }

                true
            }
        }
    }

    // 프래그먼트 교체 함수.
    private fun changeFragment(name: String) {

        val transaction = supportFragmentManager.beginTransaction()

        var goto: Fragment = HomeFragment()

        when(name) {

            // 홈 화면
            "Home" -> {
                goto = HomeFragment()
            }

            // 장소 화면
            "Place" -> {
                goto = PlaceFragment()
            }

            // 장비 화면
            "Equip" -> {
                goto = EquipFragment()
            }

            // 마이페이지 화면
            "MyPage" -> {
                goto = MyPageFragment()
            }
        }

        transaction.replace(R.id.containerMain, goto)
        transaction.commit()
    }
}