package com.ssafy.rentalfit.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.BaseActivity
import com.ssafy.rentalfit.databinding.ActivityReservationBinding
import com.ssafy.rentalfit.ui.cart.CartFragment
import com.ssafy.rentalfit.ui.equip.EquipDetailFragment
import com.ssafy.rentalfit.ui.place.PlaceDetailFragment
import com.ssafy.rentalfit.ui.place.PlaceFragment

class ReservationActivity : BaseActivity<ActivityReservationBinding>(ActivityReservationBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 기본값으로 PlaceDetail이 열리도록 설정.
        val name = intent.getStringExtra("name") ?: "PlaceDetail"

        changeFragmentReservation(name)
    }

    private fun changeFragmentReservation(name: String) {

        val transaction = supportFragmentManager.beginTransaction()

        var goto: Fragment = PlaceDetailFragment()

        when(name) {

            // 장바구니 화면
            "Cart" -> {
                goto = CartFragment()
            }

            // 장소 상세 화면
            "PlaceDetail" -> {
                goto = PlaceDetailFragment()
            }

            // 장비 상세 화면
            "EquipDetail" -> {
                goto = EquipDetailFragment()
            }
        }

        transaction.replace(R.id.fragmentContainer, goto)
        transaction.addToBackStack(name)
        transaction.commit()
    }
}
