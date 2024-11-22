package com.ssafy.rentalfit.data.remote

import com.ssafy.rentalfit.base.ApplicationClass

class RetrofitUtil {

    companion object {

        val userService = ApplicationClass.retrofit.create(UserService::class.java)
    }
}