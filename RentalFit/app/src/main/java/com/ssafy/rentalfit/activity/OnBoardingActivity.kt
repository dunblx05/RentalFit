package com.ssafy.rentalfit.activity

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.ApplicationClass

class OnBoardingActivity : AppIntro2() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = ApplicationClass.sharedPreferencesUtil.getUser()

        if (user.userId != "") {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        addSlide(
            AppIntroFragment.createInstance(
                title = "오늘은 어떤 스포츠를 즐기시나요?",
                imageDrawable = R.drawable.sports_play2,
                titleColorRes = R.color.oatmeal_main,
                backgroundColorRes = R.color.black_main,
                titleTypefaceFontRes = R.font.font_bold
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                title = "모두와 함께 플레이해요!!!",
                imageDrawable = R.drawable.sports_play1,
                titleColorRes = R.color.oatmeal_main,
                backgroundColorRes = R.color.black_main,
                titleTypefaceFontRes = R.font.font_bold
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                title = "최상의 플레이를 위한 장비!",
                imageDrawable = R.drawable.sports_play3,
                titleColorRes = R.color.oatmeal_main,
                backgroundColorRes = R.color.black_main,
                titleTypefaceFontRes = R.font.font_bold
            )
        )

        setImmersiveMode()

        setIndicatorColor(
            selectedIndicatorColor = getColor(R.color.neon_main),
            unselectedIndicatorColor = getColor(R.color.oatmeal_main)
        )
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()

    }

}