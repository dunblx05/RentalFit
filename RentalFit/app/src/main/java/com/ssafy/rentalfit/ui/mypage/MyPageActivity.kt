package com.ssafy.rentalfit.ui.mypage

import android.os.Bundle
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.BaseActivity
import com.ssafy.rentalfit.databinding.ActivityMyPageBinding
import com.ssafy.rentalfit.ui.mypage.history.HistoryFragment

class MyPageActivity : BaseActivity<ActivityMyPageBinding>(ActivityMyPageBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(R.id.myPageContainer, HistoryFragment())
            .commit()
    }
}