package com.ssafy.rentalfit.ui.place

import android.annotation.SuppressLint
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.annotation.OptIn
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MainActivity
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.data.model.dto.Place
import com.ssafy.rentalfit.databinding.FragmentEquipBinding
import com.ssafy.rentalfit.databinding.FragmentPlaceBinding
import com.ssafy.rentalfit.databinding.ListPlaceItemVerticalBinding

private const val TAG = "PlaceFragment_싸피"

class PlaceFragment : BaseFragment<FragmentPlaceBinding>(FragmentPlaceBinding::bind, R.layout.fragment_place)  {

    private lateinit var mainActivity: MainActivity
    private lateinit var placeVerticalAdapter : PlaceVerticalAdapter
    private val placeViewModel: PlaceViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showProductCntInCart(binding.toolbarPlace, R.id.menu_place_cart)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()
        settingRecyclerView()
        settingEvent()
        settingToolbar()
    }


    // ViewModel Observer 등록
    private fun registerObserver() {
        binding.apply {
            placeViewModel.placeList.observe(viewLifecycleOwner) {
                placeVerticalAdapter.updateData(it)
            }
        }
    }

    // 리사이클러뷰 설정
    private fun settingRecyclerView() {
        placeViewModel.selectPlace()

        binding.apply {
            placeVerticalAdapter = PlaceVerticalAdapter(emptyList())

            verticalRecyclerView.layoutManager = LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false)
            verticalRecyclerView.adapter = placeVerticalAdapter

        }
    }

    private fun settingEvent() {
        binding.apply {
            placeVerticalAdapter.placeVericalListener = object : PlaceVerticalAdapter.ItemClickListener{
                override fun onClick(placeId: Int) {
                    val intent = Intent(mainActivity, ReservationActivity::class.java).apply {
                        putExtra("name", "PlaceDetail")  // 예시로 장소 이름 전달
                        putExtra("placeId", placeId)
                    }
                    startActivity(intent)
                }
            }

        }
    }

    // 툴바 설정
    @SuppressLint("ResourceAsColor")
    @OptIn(ExperimentalBadgeUtils::class)
    private fun settingToolbar() {
        binding.apply {
            toolbarPlace.apply {
                inflateMenu(R.menu.menu_toolbar_place)

                mainActivity.showProductCntInCart(this, R.id.menu_place_cart)

                setOnMenuItemClickListener {
                    when(it.itemId) {
                        // 장바구니로 가기
                        R.id.menu_place_cart -> {
                            val intent = Intent(mainActivity, ReservationActivity::class.java)
                            intent.putExtra("name", "Cart")
                            startActivity(intent)
                        }
                    }
                    true
                }
            }
        }
    }
}
