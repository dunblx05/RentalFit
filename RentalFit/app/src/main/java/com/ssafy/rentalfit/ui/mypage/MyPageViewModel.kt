package com.ssafy.rentalfit.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.rentalfit.base.ApplicationClass
import com.ssafy.rentalfit.data.model.dto.User
import com.ssafy.rentalfit.data.remote.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "MyPageViewModel_싸피"
class MyPageViewModel: ViewModel() {

    private val userService = RetrofitUtil.userService

    // 회원 정보 유저 객체
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    // 회원 스탬프 개수
    private val _userStamps = MutableLiveData<Int>()
    val userStamps: LiveData<Int> get() = _userStamps

    // Preference에 등록되어 있는 유저 정보 가져오기.
    fun getUser() {
        _user.value = ApplicationClass.sharedPreferencesUtil.getUser()
        _user.value?.let {
            getStamp(it.userId)
        }
    }

    // 스탬프 가져오기
    fun getStamp(userId: String) {
        viewModelScope.launch {
            runCatching {
                userService.getUserStamps(userId)
            }.onSuccess {
                _userStamps.value = it
            }.onFailure {
                _userStamps.value = 0
            }
        }
    }

    // 유저 스탬프로 등급 나타내기
    fun getGrade(stamps: Int): Triple<String, Int, Int> {

        val ranks = arrayOf("Bronze", "Silver", "Gold", "Diamond")

        val totalLevels = 20

        val levelIdx = (stamps / 100).coerceAtMost(totalLevels - 1)

        val rankIdx = levelIdx / 5

        val rank = ranks[rankIdx]

        val levelInRank = 5 - (levelIdx % 5)

        val curPoint = stamps % 100

        return Triple(rank, levelInRank, curPoint)
    }
}