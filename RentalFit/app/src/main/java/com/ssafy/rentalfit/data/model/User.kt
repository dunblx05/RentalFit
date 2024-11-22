package com.ssafy.rentalfit.data.model

data class User(
    val userId: String,
    val userPwd: String,
    val userNickName: String,
    val userStamps: Int
) {

    constructor(): this("", "", "", 0)
    constructor(userId: String, userPwd: String): this(userId, userPwd, "", 0)
}
