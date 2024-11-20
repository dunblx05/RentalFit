package com.ssafy.rentalfit.ui.equip

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MainActivity
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentEquipBinding

class EquipFragment : BaseFragment<FragmentEquipBinding>(FragmentEquipBinding::bind, R.layout.fragment_equip) {

    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingToolbar()
    }

    // 툴바 설정
    private fun settingToolbar() {

        binding.apply {

            toolbarEquip.apply {

                inflateMenu(R.menu.menu_toolbar_equip)

                setOnMenuItemClickListener {

                    when(it.itemId) {

                        // 장바구니 화면
                        R.id.menu_equip_cart -> {

                            val intent = Intent(mainActivity, ReservationActivity::class.java)
                            intent.putExtra("name", "Cart")
                            startActivity(intent)
                        }
                    }

                    true
                }
            }
        }
    }
}
