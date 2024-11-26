package com.ssafy.rentalfit.ui.cart

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.activity.ReservationActivity
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.data.model.dto.ShoppingCart
import com.ssafy.rentalfit.databinding.FragmentCartBinding
import com.ssafy.rentalfit.ui.equip.EquipViewModel
import com.ssafy.rentalfit.ui.place.ReservationBottomSheetFragment
import com.ssafy.rentalfit.util.Utils

private const val TAG = "CartFragment_싸피"

class CartFragment: BaseFragment<FragmentCartBinding>(FragmentCartBinding::bind, R.layout.fragment_cart) {

    private lateinit var reservationActivity: ReservationActivity
    private val equipViewModel: EquipViewModel by viewModels()

    private lateinit var cartAdapter: CartAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        reservationActivity = context as ReservationActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()
        settingRecyclerView()
        settingEvent()
        settingButtonColor()
    }

    // ViewModel Observer 등록
    private fun registerObserver() {

        binding.apply {

            equipViewModel.shoppingList.observe(viewLifecycleOwner) {
                refreshList()
                equipViewModel.calculateCntPrice()
            }
        }
    }

    // 리사이클러뷰 설정
    private fun settingRecyclerView() {

        cartAdapter = CartAdapter(mutableListOf())

        refreshList()

        binding.apply {

            recyclerViewCart.layoutManager = LinearLayoutManager(reservationActivity, LinearLayoutManager.VERTICAL, false)
            recyclerViewCart.adapter = cartAdapter
        }
    }

    private fun refreshList() {

        binding.apply {
            cartAdapter.shoppingList = equipViewModel.shoppingList.value ?: mutableListOf()

            if (cartAdapter.shoppingList.isEmpty()) {
                noDataAnimation.visibility = View.VISIBLE
                buttonCartOpenBottomSheet.setEnabled(false)
            }
            else {
                noDataAnimation.visibility = View.GONE
            }

            cartAdapter.notifyDataSetChanged()
        }
    }

    // 이벤트 설정
    private fun settingEvent() {

        binding.apply {

            // 뒤로가기
            imageCartBack.setOnClickListener {
                reservationActivity.finish()
            }

            // 삭제 하기.
            cartAdapter.cartDeleteListener = object : CartAdapter.ItemDeleteListener {
                override fun onClick(position: Int, shoppingCart: ShoppingCart) {
                    cartAdapter.shoppingList.removeAt(position)
                    equipViewModel.removeItemShoppingList(shoppingCart)
                    refreshList()
                    settingButtonColor()
                }
            }

            // 수량 + 버튼
            cartAdapter.cartPlusListener = object : CartAdapter.ItemPlusListener {
                override fun onPlus(cartName: String) {
                    equipViewModel.increaseQuantity(cartName)
                }
            }

            // 수량 - 버튼
            cartAdapter.cartMinusListener = object : CartAdapter.ItemMinusListener {
                override fun onMinus(cartName: String) {
                    equipViewModel.decreaseQuantity(cartName)
                }
            }

            buttonCartOpenBottomSheet.setOnClickListener {
                if(cartAdapter.shoppingList.isEmpty()){
                    return@setOnClickListener
                }
                val bottomSheet = CartBottomSheetFragment()
                bottomSheet.show(parentFragmentManager, "CartBottomSheetFragment")
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun settingButtonColor(){
        if(cartAdapter.shoppingList.isEmpty()) {
            binding.buttonCartOpenBottomSheet.alpha = 0.5f
        }
        else{
            binding.buttonCartOpenBottomSheet.alpha = 1f
        }
    }
}