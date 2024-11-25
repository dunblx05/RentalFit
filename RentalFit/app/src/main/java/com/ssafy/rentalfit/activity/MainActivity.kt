package com.ssafy.rentalfit.activity

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide.init
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.ApplicationClass
import com.ssafy.rentalfit.base.BaseActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.data.remote.FirebaseTokenService
import com.ssafy.rentalfit.databinding.ActivityMainBinding
import com.ssafy.rentalfit.ui.equip.EquipFragment
import com.ssafy.rentalfit.ui.home.HomeFragment
import com.ssafy.rentalfit.ui.home.HomeViewModel
import com.ssafy.rentalfit.ui.mypage.MyPageFragment
import com.ssafy.rentalfit.ui.place.PlaceFragment
import com.ssafy.rentalfit.util.PermissionChecker
import com.ssafy.rentalfit.util.Utils
import java.nio.charset.Charset
import kotlin.math.log

private const val TAG = "MainActivity_싸피"

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private lateinit var nAdapter: NfcAdapter
    private lateinit var pIntent: PendingIntent

    private val homeViewModel: HomeViewModel by viewModels()

    private val checker = PermissionChecker(this)
    private val runtimePermissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        arrayOf(
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_ADVERTISE,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    } else {
        arrayOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // HomeFragment 처음에 표시
        if(savedInstanceState == null) {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.containerMain, HomeFragment())
                .commit()
        }

        checkPermission()
        settingBottomNavigation()

        // NFC Adapter 초기화
        nAdapter = NfcAdapter.getDefaultAdapter(this)
        val intent = Intent(this, javaClass).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)

        getNFCData(intent)
    }

    override fun onResume() {
        super.onResume()
        // NFC 포그라운드 디스패치 활성화
        nAdapter.enableForegroundDispatch(this, pIntent, null, null)
    }

    override fun onPause() {
        super.onPause()
        // NFC 포그라운드 디스패치 비활성화
        nAdapter.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        changeFragment("Home")
        Log.d(TAG, "onNewIntent: ")
    }

    private fun checkPermission() {
        /** permission check **/
        if (!checker.checkPermission(this, runtimePermissions)) {
            checker.setOnGrantedListener{ //퍼미션 획득 성공일때
                init()
            }

            checker.requestPermissionLauncher.launch(runtimePermissions) // 권한없으면 창 띄움
        } else { //이미 전체 권한이 있는 경우
            init()
        }
        /** permission check **/
    }

    private fun init(){
        initFCM()
        createNotificationChannel(channel_id, "ssafy")
    }

    private fun initFCM(){
        // FCM 토큰 수신


    }

    // Notification 수신을 위한 체널 추가
    private fun createNotificationChannel(id: String, name: String) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(NotificationChannel(id, name, importance))
        }
    }

    private fun getNFCData(intent: Intent) {

        val action = intent.action

        if (action == NfcAdapter.ACTION_NDEF_DISCOVERED || action == NfcAdapter.ACTION_TECH_DISCOVERED || action == NfcAdapter.ACTION_TAG_DISCOVERED) {

            val messages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)

            messages?.forEach {

                val message = it as NdefMessage

                message.records.forEach { record ->

                    val payload = record.payload
                    val text = String(payload, Charset.forName("UTF-8"))

                    when(text) {

                        "시작" -> {
                            homeViewModel.setNfcTagValue("시작")
                        }

                        "끝" -> {
                            homeViewModel.setNfcTagValue("끝")
                        }

                        else -> {
                            homeViewModel.setNfcTagValue("유효하지 않은 NFC 태그")
                        }
                    }
                }
            }
        }
    }

    // 바텀 네비게이션 설정
    private fun settingBottomNavigation() {

        binding.apply {

            bottomNaviMain.setOnItemSelectedListener {

                when(it.itemId) {

                    // 홈
                    R.id.menu_bottom_home -> {
                        changeFragment("Home")
                    }

                    // 장소
                    R.id.menu_bottom_place -> {
                        changeFragment("Place")
                    }

                    // 장비
                    R.id.menu_bottom_equip -> {
                        changeFragment("Equip")
                    }

                    // 마이페이지
                    R.id.menu_bottom_mypage -> {
                        changeFragment("MyPage")
                    }
                }

                true
            }
        }
    }


    // 프래그먼트 교체 함수.
    private fun changeFragment(name: String) {

        val transaction = supportFragmentManager.beginTransaction()

        var goto: Fragment = HomeFragment()
        val menu = binding.bottomNaviMain.menu

        when (name) {

            "Home" -> {
                goto = HomeFragment()
                menu.findItem(R.id.menu_bottom_home).isChecked = true
            }

            "Place" -> {
                goto = PlaceFragment()
                menu.findItem(R.id.menu_bottom_place).isChecked = true
            }

            "Equip" -> {
                goto = EquipFragment()
                menu.findItem(R.id.menu_bottom_equip).isChecked = true
            }

            "MyPage" -> {
                goto = MyPageFragment()
                menu.findItem(R.id.menu_bottom_mypage).isChecked = true
            }
        }

        transaction.replace(R.id.containerMain, goto)
        transaction.commit()
    }

    companion object{
        // Notification Channel ID
        const val channel_id = "ssafy_channel"
        // ratrofit  수업 후 network 에 업로드 할 수 있도록 구성
        fun uploadToken(token:String){

            val firebaseService = ApplicationClass.retrofit.create(FirebaseTokenService::class.java)

        }
    }
}
