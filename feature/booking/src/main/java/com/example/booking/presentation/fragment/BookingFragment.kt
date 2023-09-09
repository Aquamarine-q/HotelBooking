package com.example.booking.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Patterns
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
import com.redmadrobot.inputmask.MaskedTextChangedListener

private const val PHONE_NUMBER_MASK = "+7 ([000]) [000]-[00]-[00]"

class BookingFragment : BaseFragment<FragmentBookingBinding>(
    FragmentBookingBinding::inflate
) {
    private val viewModel by viewModels<BookingViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        viewModel.viewState.observe(viewLifecycleOwner) { model -> renderModel(model) }
        viewModel.onScreenOpened()
    }

    private fun setUpListeners() {
        with(binding) {
            toolbar.setNavigationOnClickListener {
                findNavController().navigate(Uri.parse("hotelBooking://number"))
            }

            val maskedTextChangedListener = MaskedTextChangedListener(PHONE_NUMBER_MASK, number)
            number.addTextChangedListener(maskedTextChangedListener)

            val adapter = context?.let { TouristAdapter(it) }
            tourists.adapter = adapter
            addButton.setOnClickListener {
                adapter?.addTourist()
            }

            payButton.setOnClickListener {
                val numberText = number.text.toString()
                val mailText = mail.text.toString()
                if (numberText.isNotEmpty() && isMailValid(mailText)) {
                    findNavController().navigate(R.id.action_bookingFragment_to_paidFragment)
                } else {
                    number.error = getString(R.string.error)
                    mail.error = getString(R.string.error)
                }
            }
        }
    }

    private fun isMailValid(mail: String): Boolean {
        return mail.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(mail).matches()
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
            payButton.text = getString(
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