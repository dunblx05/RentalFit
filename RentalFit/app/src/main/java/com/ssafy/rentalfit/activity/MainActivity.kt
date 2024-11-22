package com.ssafy.rentalfit.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import kotlin.math.log

private const val TAG = "MainActivity_싸피"

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

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        changeFragment("Home")
        Log.d(TAG, "onNewIntent: ")
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
        val menu = binding.bottomNaviMain.menu

        when (name) {
            "Home" -> {
                goto = HomeFragment()
                menu.findItem(R.id.menu_bottom_home).isChecked = true
            }
            "Place" -> {
                goto = PlaceFragment()
                menu.findItem(R.id.menu_bottom_place).isChecked = true
            }
            "Equip" -> {
                goto = EquipFragment()
                menu.findItem(R.id.menu_bottom_equip).isChecked = true
            }
            "MyPage" -> {
                goto = MyPageFragment()
                menu.findItem(R.id.menu_bottom_mypage).isChecked = true
            }
        }

        transaction.replace(R.id.containerMain, goto)
        transaction.commit()
    }
}