package com.example.booking.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.booking.R
import com.example.booking.databinding.FragmentPaidBinding
import com.example.booking.di.BookingComponent
import com.example.core.base.BaseFragment
import com.example.core.di.provider.ApplicationProvider
import kotlin.random.Random

class PaidFragment : BaseFragment<FragmentPaidBinding>(
    FragmentPaidBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            subtitle.text = getString(R.string.order_confirmed, Random.nextInt().toString())
            toolbar.setNavigationOnClickListener {
                findNavController().navigate(R.id.action_paidFragment_to_bookingFragment)
            }
            editButton.setOnClickListener {
                findNavController().navigate(Uri.parse("hotelBooking://hotel"))
            }
        }
    }

    override fun inject(applicationProvider: ApplicationProvider) {
        BookingComponent.init(applicationProvider).inject(this)
    }
}