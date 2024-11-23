package com.ssafy.rentalfit.data.remote

import com.ssafy.rentalfit.data.model.dto.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {

    // 사용자 정보를 추가한다.
    @POST("rest/user")
    suspend fun insertUser(@Body body: User): Boolean

    // 로그인 처리 후 성공적으로 로그인 되었다면 loginId라는 쿠키를 내려 보낸다.
    @POST("rest/user/login")
    suspend fun login(@Body body: User): User

    // request parameter로 전달된 id가 이미 사용 중인지 반환한다.
    @GET("rest/user/isUsed")
    suspend fun isUsedId(@Query("id") userId: String): Boolean

    // 전달된 id의 스탬프 개수를 반환한다.
    @GET("rest/user/stamp")
    suspend fun getUserStamps(@Query("id") userId: String): Int
}