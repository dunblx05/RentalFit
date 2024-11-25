package com.ssafy.rentalfit.ui.equip

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.MainActivity
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.data.model.dto.Equip
import com.ssafy.rentalfit.databinding.FragmentEquipBinding

class EquipFragment : BaseFragment<FragmentEquipBinding>(FragmentEquipBinding::bind, R.layout.fragment_equip) {

    private lateinit var mainActivity: MainActivity
    private val equipViewModel: EquipViewModel by viewModels()

    private lateinit var equipVerticalAdapter: EquipVerticalAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()
        settingToolbar()
        settingRecyclerView()
        settingEvent()
    }

    // ViewModel Observer 등록
    private fun registerObserver() {

        binding.apply {

            equipViewModel.equipList.observe(viewLifecycleOwner) {
                equipVerticalAdapter.updateData(it)
            }
        }
    }

    // 툴바 설정
    private fun settingToolbar() {

        binding.apply {

            toolbarEquip.apply {

                inflateMenu(R.menu.menu_toolbar_equip)

                setOnMenuItemClickListener {

                    when(it.itemId) {

                        // 장바구니 화면
                        R.id.menu_equip_cart -> {

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

    // 리사이클러뷰 설정
    private fun settingRecyclerView() {

        equipViewModel.selectEquip()

        binding.apply {

            equipVerticalAdapter = EquipVerticalAdapter(emptyList())

            recyclerViewEquipVertical.layoutManager = LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false)
            recyclerViewEquipVertical.adapter = equipVerticalAdapter
        }
    }

    // 이벤트 설정
    private fun settingEvent() {

        binding.apply {

            // 장비 아이템 하나 클릭한다면
            equipVerticalAdapter.equipVerticalListener = object : EquipVerticalAdapter.ItemClickListener {
                override fun onClick(equipId: Int) {

                    val intent = Intent(mainActivity, ReservationActivity::class.java)
                    intent.putExtra("name", "EquipDetail")
                    intent.putExtra("equipId", equipId)
                    startActivity(intent)
                }
            }
        }
    }
}