package com.ssafy.rentalfit.ui.login

import android.content.Context
import android.os.Bundle
import android.view.View
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.LoginActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentJoinBinding

class JoinFragment: BaseFragment<FragmentJoinBinding>(FragmentJoinBinding::bind, R.layout.fragment_join) {

    private lateinit var loginActivity: LoginActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginActivity = context as LoginActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingEvent()
    }

    // 이벤트 설정
    private fun settingEvent() {

        binding.apply {

            // 아이디 중복 확인.
            buttonJoinCheckId.setOnClickListener {

            }

            // 회원가입 버튼
            buttonJoinJoin.setOnClickListener {

                // 검사 다 완료된다면..
                parentFragmentManager.popBackStack()
            }
        }
    }
}