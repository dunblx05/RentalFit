package com.ssafy.rentalfit.ui.equip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentEquipDetailBinding

class EquipDetailFragment : BaseFragment<FragmentEquipDetailBinding>(
    bind = { view -> FragmentEquipDetailBinding.bind(view) },
    layoutResId = R.layout.fragment_equip
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
