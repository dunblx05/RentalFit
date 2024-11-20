package com.ssafy.rentalfit.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.BaseActivity
import com.ssafy.rentalfit.databinding.ActivityLoginBinding
import com.ssafy.rentalfit.ui.login.LoginFragment

class LoginActivity: BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // LoginFragment 처음에 표시. 추후 로그인 되어있는지 아닌지로 파악. 관통 network_starter 확인.
        if(savedInstanceState == null) {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.containerLogin, LoginFragment())
                .addToBackStack(null)
                .commit()
        }
    }


    fun changeFragmentLogin(name: String) {

        val transaction = supportFragmentManager.beginTransaction()

        var goto: Fragment = LoginFragment()

        when(name) {

            // 로그인
            "Login" -> {
                goto = LoginFragment()
            }

            // 회원가입
            "Join" -> {

            }
        }

        transaction.replace(R.id.containerLogin, goto)
        transaction.commit()
    }
}