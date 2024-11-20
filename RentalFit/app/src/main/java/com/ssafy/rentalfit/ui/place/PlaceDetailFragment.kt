package com.ssafy.rentalfit.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.rentalfit.R
import com.ssafy.rentalfit.base.BaseFragment
import com.ssafy.rentalfit.databinding.FragmentPlaceDetailBinding

class PlaceDetailFragment : BaseFragment<FragmentPlaceDetailBinding>(
    bind = { view -> FragmentPlaceDetailBinding.bind(view) },
    layoutResId = R.layout.fragment_equip
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
