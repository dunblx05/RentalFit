package com.ssafy.rentalfit.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.LoginActivity
import com.ssafy.rentalfit.activity.MainActivity
import com.ssafy.rentalfit.base.ApplicationClass
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.data.model.dto.User
import com.ssafy.rentalfit.databinding.FragmentLoginBinding
import com.ssafy.rentalfit.util.Utils

private const val TAG = "LoginFragment_싸피"
class LoginFragment: BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::bind, R.layout.fragment_login) {

    private lateinit var loginActivity: LoginActivity

    private val loginViewModel: LoginViewModel by viewModels()

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginActivity = context as LoginActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(loginActivity, gso)

        auth = Firebase.auth

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

            // 구글 로그인
            buttonLoginWithGoogle.setOnClickListener {
                Log.d(TAG, "settingEvent: ")
                signInWithGoogle()
            }
        }
    }

    private fun signInWithGoogle() {

        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->

        if(result.resultCode == AppCompatActivity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

            try {
                val account = task.getResult(ApiException::class.java)
                Log.d(TAG, "firebaseAuthWithGoogle: " + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            }
            catch (e: Exception) {
                Log.d(TAG, "Google sign in failed: ", e)
                Utils.showCustomToast(loginActivity, "Google 로그인에 실패했습니다.")
            }
        }
        else {
            Utils.showCustomToast(loginActivity, "Google 로그인 취소 또는 실패")
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(loginActivity) { task ->
                if (task.isSuccessful) {
                    // 로그인 성공
                    val authUser = auth.currentUser

                    if(authUser != null) {
                        // 필요한 경우 추가로 사용자 정보를 ViewModel이나 서버로 전달
                        // 여기서는 MainActivity로 이동

                        val user = User(
                            userId = authUser.uid,
                            userPwd = "",
                            userNickName = authUser.displayName!!,
                            userStamps = 0
                        )

                        loginViewModel.insertUser(user)

                        ApplicationClass.sharedPreferencesUtil.addUser(user)

                        Utils.showCustomToast(loginActivity, "Google 로그인 성공")

                        val intent = Intent(loginActivity, MainActivity::class.java)
                        startActivity(intent)

                        loginActivity.finish()
                    }
                } else {
                    // 로그인 실패
                    Log.d(TAG, "signInWithCredential:failure", task.exception)
                    Utils.showCustomToast(loginActivity, "Firebase 인증에 실패했습니다.")
                }
            }
    }

}