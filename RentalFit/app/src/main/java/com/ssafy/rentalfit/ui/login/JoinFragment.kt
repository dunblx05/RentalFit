package com.ssafy.rentalfit.ui.login

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.LoginActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.data.model.dto.User
import com.ssafy.rentalfit.databinding.FragmentJoinBinding
import com.ssafy.rentalfit.util.Utils

class JoinFragment: BaseFragment<FragmentJoinBinding>(FragmentJoinBinding::bind, R.layout.fragment_join) {

    private lateinit var loginActivity: LoginActivity

    private val loginViewModel: LoginViewModel by viewModels()

    private var checkedId = false

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

        loginViewModel.isUsed.observe(viewLifecycleOwner) {

            // 중복되지 않는다면.
            if(it) {
                checkedId = true
                Utils.showCustomToast(loginActivity, "사용 가능한 ID 입니다.")
            }
            // 중복 된다면.
            else {
                checkedId = false
                Utils.showCustomToast(loginActivity, "이미 사용 중인 ID 입니다.")
            }
        }

        loginViewModel.insertResult.observe(viewLifecycleOwner) {

            // 회원 가입에 성공했다면.
            if(it) {
                Utils.showCustomToast(loginActivity, "회원가입에 성공했습니다.")
                parentFragmentManager.popBackStack()
            }
            // 회원가입에 실패했다면.
            else {
                Utils.showCustomToast(loginActivity, "회원가입에 실패했습니다. 다시 시도해 주세요.")
            }
        }
    }

    // 이벤트 설정
    private fun settingEvent() {

        binding.apply {

            // 아이디 중복 확인.
            buttonJoinCheckId.setOnClickListener {

                val userId = editTextJoinId.text.toString()
                if(userId.isEmpty()) {
                    Utils.showCustomToast(loginActivity, "ID를 입력해주세요.")
                    return@setOnClickListener
                }
                loginViewModel.isUsedId(userId)
            }

            // 회원가입 버튼
            buttonJoinJoin.setOnClickListener {

                val userId = editTextJoinId.text.toString()
                val userPwd = editTextJoinPw.text.toString()
                val userPwd2 = editTextJoinPw2.text.toString()
                val userNickName = editTextJoinNickname.text.toString()

                // 아이디를 입력 안 했다면.
                if(userId.isEmpty()) {
                    Utils.showCustomToast(loginActivity, "ID를 입력해주세요.")
                }
                // 아이디 중복 체크를 안 했다면.
                else if(!checkedId) {
                    Utils.showCustomToast(loginActivity, "ID 중복 확인을 해주세요.")
                }
                // 비밀번호를 입력 안 했다면.
                else if(userPwd.isEmpty() || userPwd2.isEmpty()) {
                    Utils.showCustomToast(loginActivity, "비밀번호를 입력해주세요.")
                }
                // 비밀번호와 비밀번호 확인이 다르다면.
                else if(!userPwd.equals(userPwd2)) {
                    layoutTextJoinPw2.error
                    Utils.showCustomToast(loginActivity, "비밀번호를 다시 확인해주세요.")
                }
                // 별명 입력 안 했다면.
                else if(userNickName.isEmpty()) {
                    Utils.showCustomToast(loginActivity, "닉네임을 입력해주세요")
                }
                // 검사 다 완료된다면..
                else {

                    val user = User(
                        userId = userId,
                        userPwd = userPwd,
                        userNickName = userNickName,
                        userStamps = 0,
                    )

                    loginViewModel.insertUser(user)
                }
            }
        }
    }
}