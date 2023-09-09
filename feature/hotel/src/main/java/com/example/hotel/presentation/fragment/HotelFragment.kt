package com.example.hotel.presentation.fragment

import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.base.BaseFragment
import com.example.core.di.provider.ApplicationProvider
import com.example.hotel.R
import com.example.hotel.databinding.FragmentHotelBinding
import com.example.hotel.di.HotelComponent
import com.example.core.adapter.PagerAdapter
import com.example.hotel.presentation.viewmodel.HotelViewModel
import com.example.hotel.presentation.viewstate.HotelViewState
import com.google.android.material.tabs.TabLayoutMediator
import com.example.core.R as coreResources

class HotelFragment : BaseFragment<FragmentHotelBinding>(FragmentHotelBinding::inflate) {

    private val viewModel by viewModels<HotelViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner) { model -> renderModel(model) }
        viewModel.onScreenOpened()
    }

    private fun renderModel(model: HotelViewState) {
        with(binding) {
            viewPager.adapter = PagerAdapter(model.hotelPhoto)
            TabLayoutMediator(
                tabLayout, viewPager
            ) { tab, position ->
                tab.icon = if (position in 0 until model.hotelPhoto.size) {
                    ContextCompat.getDrawable(requireContext(), coreResources.drawable.tab_selector)
                } else {
                    throw Resources.NotFoundException("Position not found")
                }
            }.attach()

            button.setOnClickListener {
                findNavController().navigate(Uri.parse("hotelBooking://number/" + model.name))
            }

            rating.text = getString(R.string.space, model.rating, model.ratingName)
            title.text = model.name
            address.text = model.address
            price.text = getString(
                R.string.price,
                model.minimalPrice.substring(0, 3),
                model.minimalPrice.substring(3, 6),
            )
            priceForIt.text = model.priceForIt
            firstPeculiarity.text = model.peculiarities[0]
            secondPeculiarity.text = model.peculiarities[1]
            thirdPeculiarity.text = model.peculiarities[2]
            forthPeculiarity.text = model.peculiarities[3]
            hotelDescription.text = model.description
        }
    }

    override fun inject(applicationProvider: ApplicationProvider) {
        HotelComponent.init(applicationProvider).inject(this)
    }
}