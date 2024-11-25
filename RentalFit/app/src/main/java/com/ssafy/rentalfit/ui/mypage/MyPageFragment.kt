package com.ssafy.rentalfit.ui.mypage

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MainActivity
import com.ssafy.rentalfit.activity.MyPageActivity
import com.ssafy.rentalfit.base.ApplicationClass
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentMyPageBinding

private const val TAG = "MyPageFragment_싸피"

class MyPageFragment :
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {

    private lateinit var mainActivity: MainActivity

    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()
        initView()
        settingToolbar()
        settingEvent()
    }

    // 뷰 초기화.
    private fun initView() {
        myPageViewModel.getUser()
    }

    // ViewModel Observer 등록
    private fun registerObserver() {

        myPageViewModel.user.observe(viewLifecycleOwner) {

            // 있다면.
            if (it.userId.isNotEmpty()) {

                binding.apply {

                    // 닉네임 표시.
                    textMyPageNickname.text = it.userNickName

                    myPageViewModel.getStamp(it.userId)
                }
            }
        }

        myPageViewModel.userStamps.observe(viewLifecycleOwner) {

            val triple = myPageViewModel.getGrade(it)
            settingGrade(triple)
        }
    }

    // 툴바 설정
    private fun settingToolbar() {

        binding.apply {

            toolbarMyPage.apply {

                inflateMenu(R.menu.menu_toolbar_my_page)

                setOnMenuItemClickListener {

                    when (it.itemId) {

                        // 알림 화면
                        R.id.menu_my_page_alarm -> {

                            val intent = Intent(mainActivity, MyPageActivity::class.java)
                            intent.putExtra("name", "Alarm")
                            startActivity(intent)
                        }

                        // 로그아웃
                        R.id.menu_my_page_logout -> {

                            // 구글 로그인 로그아웃
                            FirebaseAuth.getInstance().signOut()

                            // Preference 지우기
                            ApplicationClass.sharedPreferencesUtil.deleteUser()
                            ApplicationClass.sharedPreferencesUtil.deleteUserCookie()

                            // 화면 이동
                            mainActivity.finish()
                        }
                    }

                    true
                }
            }
        }
    }

    // 이벤트 설정
    private fun settingEvent() {

        binding.apply {

            // 장소 예약 화면
            textMyPagePlaceHistory.setOnClickListener {

                val intent = Intent(mainActivity, MyPageActivity::class.java)
                intent.putExtra("name", "PlaceHistory")
                startActivity(intent)
            }

            // 장비 예약 화면
            textMyPageEquipHistory.setOnClickListener {

                val intent = Intent(mainActivity, MyPageActivity::class.java)
                intent.putExtra("name", "EquipHistory")
                startActivity(intent)
            }

            textMap.setOnClickListener {
                val intent = Intent(mainActivity, MyPageActivity::class.java)
                intent.putExtra("name", "Map")
                startActivity(intent)
            }

            textTel.setOnClickListener {
                val phoneNumber = "01012345678" // 전화번호
                val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }
                startActivity(dialIntent)
            }

        }
    }

    // 등급 표시
    private fun settingGrade(triple: Triple<String, Int, Int>) {

        binding.apply {

            val (rank, levelInRank, curPoint) = triple

            val colorResId = when (rank) {
                "Bronze" -> R.color.bronze
                "Silver" -> R.color.silver
                "Gold" -> R.color.gold
                "Diamond" -> R.color.diamond
                else -> R.color.oatmeal_main
            }

            imageMyPageGrade.setColorFilter(ContextCompat.getColor(mainActivity, colorResId))
            textMyPageGrade.setTextColor(ContextCompat.getColor(mainActivity, colorResId))
            textMyPageGrade.text = "$rank $levelInRank"
            progressBarMyPageGrade.progress = curPoint
            textMyPageProgressGradeNow.text = curPoint.toString()
        }
    }
}