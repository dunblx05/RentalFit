package com.ssafy.rentalfit.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.LoginActivity
import com.ssafy.rentalfit.activity.MainActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentLoginBinding

class LoginFragment: BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::bind, R.layout.fragment_login) {

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

            // 로그인 버튼을 누른다면.
            buttonLoginLogin.setOnClickListener {

                val intent = Intent(loginActivity, MainActivity::class.java)
                startActivity(intent)
                loginActivity.finish()
            }

            // 회원가입 버튼을 누른다면.
            buttonLoginJoin.setOnClickListener {

                loginActivity.changeFragmentLogin("Join")
            }
        }
    }

}