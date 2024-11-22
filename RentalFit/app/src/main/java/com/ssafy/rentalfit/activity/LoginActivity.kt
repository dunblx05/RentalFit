package com.ssafy.rentalfit.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.ApplicationClass
import com.ssafy.rentalfit.base.BaseActivity
import com.ssafy.rentalfit.databinding.ActivityLoginBinding
import com.ssafy.rentalfit.ui.login.JoinFragment
import com.ssafy.rentalfit.ui.login.LoginFragment

class LoginActivity: BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = ApplicationClass.sharedPreferencesUtil.getUser()

        // 로그인 상태 확인. userId가 있다면 로그인 된 상태
        if(user.userId != "") {

            // 바로 MainActivity 띄우기.
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        // 로그인 되어 있지 않다면 첫 화면은 LoginFragment
        else {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.containerLogin, LoginFragment())
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
                goto = JoinFragment()
            }
        }

        transaction.replace(R.id.containerLogin, goto)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}