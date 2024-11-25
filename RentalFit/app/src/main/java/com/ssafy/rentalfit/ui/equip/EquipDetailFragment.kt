package com.ssafy.rentalfit.ui.equip

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.base.ApplicationClass.Companion.SERVER_URL
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.data.model.dto.Equip
import com.ssafy.rentalfit.data.model.dto.ShoppingCart
import com.ssafy.rentalfit.databinding.FragmentEquipDetailBinding
import com.ssafy.rentalfit.util.Utils

private const val TAG = "EquipDetailFragment_싸피"
class EquipDetailFragment : BaseFragment<FragmentEquipDetailBinding>(FragmentEquipDetailBinding::bind, R.layout.fragment_equip_detail) {

    private lateinit var reservationActivity: ReservationActivity
    private val equipViewModel: EquipViewModel by viewModels()

    private var equipId: Int = -1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        reservationActivity = context as ReservationActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            equipId = it.getInt("equipId", -1)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()
        settingEvent()
        
        if(equipId != -1) {
            equipViewModel.selectEquipById(equipId)
        }
        else {
            Log.d(TAG, "onViewCreated: 정보를 불러올 수 없습니다.")
        }
    }

    // ViewModel Observer 등록
    private fun registerObserver() {

        binding.apply {

            // 장비 객체 관찰
            equipViewModel.equip.observe(viewLifecycleOwner) { equip ->
                loadEquipInfo(equip)
            }

            // 장비 수량 관찰
            equipViewModel.quantity.observe(viewLifecycleOwner) { quantity ->
                textEquipDetailCnt.text = quantity.toString()
            }
        }
    }

    // 정보 가져오기
    private fun loadEquipInfo(equip: Equip) {

        binding.apply {

            // 이름
            textEquipDetailName.text = equip.equipName

            // 사진
            Glide.with(reservationActivity)
                .load("${SERVER_URL}images/${equip.equipImg}")
                .into(imageEquipDetail)

            // 가격
            textEquipDetailPrice.text = Utils.makeComma(equip.equipPrice)

            // 설명
            textEquipDetailDescription.text = equip.equipDetail
        }
    }

    // 이벤트 설정
    private fun settingEvent() {

        binding.apply {

            // 뒤로 가기
            imageEquipDetailBack.setOnClickListener {
                reservationActivity.finish()
            }

            // 장바구니로 가기.
            buttonEquipDetailOpenCart.setOnClickListener {

                equipViewModel.equip.value?.let { equip ->
                    ShoppingCart(
                        cartId = equip.equipId,
                        cartImg = equip.equipImg,
                        cartName = equip.equipName,
                        cartCnt = equipViewModel.quantity.value!!,
                        cartPrice = equip.equipPrice
                    )
                }?.apply {
                    equipViewModel.addShoppingList(this)
                    reservationActivity.changeFragmentReservation("Cart")
                }
            }

            // 수량 -
            imageEquipDetailMinus.setOnClickListener {
                equipViewModel.minusQuantity()
            }

            // 수량 +
            imageEquipDetailPlus.setOnClickListener {
                equipViewModel.plusQuantity()
            }
        }
    }
}
