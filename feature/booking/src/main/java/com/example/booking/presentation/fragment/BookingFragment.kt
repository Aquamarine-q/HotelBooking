package com.example.booking.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.booking.R
import com.example.booking.databinding.FragmentBookingBinding
import com.example.booking.di.BookingComponent
import com.example.booking.presentation.adapter.TouristAdapter
import com.example.booking.presentation.viewmodel.BookingViewModel
import com.example.booking.presentation.viewstate.BookingViewState
import com.example.core.base.BaseFragment
import com.example.core.di.provider.ApplicationProvider

class BookingFragment : BaseFragment<FragmentBookingBinding>(
    FragmentBookingBinding::inflate
) {
    private val viewModel by viewModels<BookingViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val numbers = mutableListOf(resources.getStringArray(R.array.tourists)[0])
        val adapter = TouristAdapter(numbers)
        with(binding) {
            toolbar.setNavigationOnClickListener {
                findNavController().navigate(Uri.parse("hotelBooking://number"))
            }
            tourists.adapter = adapter
            addButton.setOnClickListener {
                numbers.add(getString(R.string.tourist))
                tourists.adapter = TouristAdapter(numbers)
                adapter.notifyItemInserted(numbers.size - 1)
            }
            button.setOnClickListener {
                findNavController().navigate(R.id.action_bookingFragment_to_paidFragment)
            }
        }
        viewModel.viewState.observe(viewLifecycleOwner) { model -> renderModel(model) }
        viewModel.onScreenOpened()
    }

    private fun renderModel(model: BookingViewState) {
        with(binding) {
            rating.text = getString(R.string.blank, model.horating, model.ratingName)
            title.text = model.hotelName
            address.text = model.hotelAddress
            departure.text = model.departure
            country.text = model.arrivalCountry
            date.text = getString(R.string.dash, model.tourDateStart, model.tourDateStop)
            nightCount.text = getString(R.string.nights, model.nightsCount)
            hotel.text = model.hotelName
            room.text = model.room
            nutrition.text = model.nutrition
            tourCost.text = getString(
                R.string.ruble,
                model.tourPrice.toString().substring(0, 3),
                model.tourPrice.toString().substring(3, 6)
            )
            fuelSurcharge.text = getString(
                R.string.ruble,
                model.fuelCharge.toString().substring(0, 1),
                model.fuelCharge.toString().substring(1, 4)
            )
            serviceCharge.text = getString(
                R.string.ruble,
                model.serviceCharge.toString().substring(0, 1),
                model.serviceCharge.toString().substring(1, 4)
            )
            toPay.text = getString(
                R.string.ruble,
                model.toPay.toString().substring(0, 3),
                model.toPay.toString().substring(3, 6)
            )
            button.text = getString(
                R.string.pay,
                model.toPay.toString().substring(0, 3),
                model.toPay.toString().substring(3, 6)
            )
        }
    }

    override fun inject(applicationProvider: ApplicationProvider) {
        BookingComponent.init(applicationProvider).inject(this)
    }
}