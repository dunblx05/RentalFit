package com.ssafy.rentalfit.ui.equip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentEquipBinding

class EquipFragment : BaseFragment<FragmentEquipBinding>(
    bind = { view -> FragmentEquipBinding.bind(view) },
    layoutResId = R.layout.fragment_equip
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
