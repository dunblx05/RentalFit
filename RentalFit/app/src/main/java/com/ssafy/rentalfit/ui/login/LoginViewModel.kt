package com.ssafy.rentalfit.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.rentalfit.data.model.dto.User
import com.ssafy.rentalfit.data.remote.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "LoginViewModel_싸피"
class LoginViewModel: ViewModel() {

    private val userService = RetrofitUtil.userService

    // 로그인 유저 객체
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    // 회원가입 아이디 중복 확인 여부
    private val _isUsed = MutableLiveData<Boolean>()
    val isUsed: LiveData<Boolean> get() = _isUsed

    // 회원가입 성공 여부
    private val _insertResult = MutableLiveData<Boolean>()
    val insertResult: LiveData<Boolean> get() = _insertResult

    // 로그인.
    fun login(userId: String, userPwd:String){
        viewModelScope.launch {
            runCatching {
                userService.login(User(userId, userPwd))
            }.onSuccess {
                _user.value = it
            }.onFailure {
                _user.value = User()
            }
        }
    }

    // id 중복 확인.
    fun isUsedId(userId: String) {
        viewModelScope.launch {
            runCatching {
                userService.isUsedId(userId)
            }.onSuccess {
                _isUsed.value = !it
            }.onFailure {
                _isUsed.value = false
            }
        }
    }

    // 회원가입
    fun insertUser(user: User) {
        viewModelScope.launch {
            runCatching {
                userService.insertUser(user)
            }.onSuccess {
                _insertResult.value = it
            }.onFailure {
                _insertResult.value = false
            }
        }
    }
}