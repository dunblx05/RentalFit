package com.ssafy.rentalfit.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.ssafy.rentalfit.data.model.dto.User

class SharedPreferencesUtil (context: Context) {
    val SHARED_PREFERENCES_NAME = "rentalfit_preference"
    val COOKIES_KEY_NAME = "cookies"

    var preferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    //사용자 정보 저장
    fun addUser(user: User){
        val editor = preferences.edit()
        editor.putString("userId", user.userId)
        editor.putString("userNickname", user.userNickName)
        editor.apply()
    }

    fun getUser(): User {
        val id = preferences.getString("userId", "")
        if (id != ""){
            val name = preferences.getString("userNickname", "")
            return User(id!!, "", name!!,0)
        }else{
            return User()
        }
    }

    fun deleteUser(){
        //preference 지우기
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }

    fun addUserCookie(cookies: HashSet<String>) {
        val editor = preferences.edit()
        editor.putStringSet(COOKIES_KEY_NAME, cookies)
        editor.apply()
    }

    fun getUserCookie(): MutableSet<String>? {
        return preferences.getStringSet(COOKIES_KEY_NAME, HashSet())
    }

    fun deleteUserCookie() {
        preferences.edit().remove(COOKIES_KEY_NAME).apply()
    }

    fun addNotice(info: String) {
        val list = getNotice()

        list.add(info)
        val json = Gson().toJson(list)

        preferences.edit().let {
            it.putString("notice", json)
            it.apply()
        }
    }

    fun setNotice(list: MutableList<String>) {
        preferences.edit().let {
            it.putString("notice", Gson().toJson(list))
            it.apply()
        }
    }

    fun getNotice() : MutableList<String> {
        val str = preferences.getString("notice", "")!!
        val list = if(str.isEmpty()) mutableListOf<String>() else Gson().fromJson(str, MutableList::class.java) as MutableList<String>

        return list
    }
}