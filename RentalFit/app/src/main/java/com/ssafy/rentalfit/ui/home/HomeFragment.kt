package com.ssafy.rentalfit.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MainActivity
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

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

            toolbarHome.apply {

                inflateMenu(R.menu.menu_toolbar_home)

                setOnMenuItemClickListener {

                    when(it.itemId) {

                        // 장바구니
                        R.id.menu_home_cart -> {

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