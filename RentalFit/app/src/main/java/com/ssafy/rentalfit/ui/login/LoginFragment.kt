package com.ssafy.rentalfit.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.LoginActivity
import com.ssafy.rentalfit.activity.MainActivity
import com.ssafy.rentalfit.base.ApplicationClass
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentLoginBinding
import com.ssafy.rentalfit.util.Utils

private const val TAG = "LoginFragment_싸피"
class LoginFragment: BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::bind, R.layout.fragment_login) {

    private lateinit var loginActivity: LoginActivity

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginActivity = context as LoginActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()
        settingEvent()
    }

    // ViewModel Observer 등록
    private fun registerObserver() {

        loginViewModel.user.observe(viewLifecycleOwner) {

            // 아이디 입력 X
            if(it.userId.isEmpty()) {
                Utils.showCustomToast(loginActivity, "ID, 비밀번호를 확인해주세요.")
            }
            // 로그인 성공
            else {
                ApplicationClass.sharedPreferencesUtil.addUser(it)
                Utils.showCustomToast(loginActivity, "로그인 되었습니다.")

                val intent = Intent(loginActivity, MainActivity::class.java)
                startActivity(intent)
                loginActivity.finish()
            }
        }
    }

    // 이벤트 설정
    private fun settingEvent() {

        binding.apply {

            // 로그인 버튼을 누른다면.
            buttonLoginLogin.setOnClickListener {

                val userId = editTextLoginId.text.toString()
                val userPwd = editTextLoginPw.text.toString()

                loginViewModel.login(userId, userPwd)
            }

            // 회원가입 버튼을 누른다면.
            buttonLoginJoin.setOnClickListener {

                loginActivity.changeFragmentLogin("Join")
            }
        }
    }

}