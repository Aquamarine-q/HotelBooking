package com.example.number.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.base.BaseFragment
import com.example.core.di.provider.ApplicationProvider
import com.example.number.data.model.Rooms
import com.example.number.databinding.FragmentNumberBinding
import com.example.number.di.NumberComponent
import com.example.number.presentation.adapter.NumberAdapter
import com.example.number.presentation.viewmodel.NumberViewModel

class NumberFragment : BaseFragment<FragmentNumberBinding>(
    FragmentNumberBinding::inflate
) {
    private val viewModel by viewModels<NumberViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            toolbar.title = arguments?.getString("hotelName")
            toolbar.setNavigationOnClickListener {
                findNavController().navigate(Uri.parse("hotelBooking://hotel"))
            }
        }
        viewModel.viewState.observe(viewLifecycleOwner) { model -> renderModel(model) }
        viewModel.onScreenOpened()
    }

    private fun renderModel(model: Rooms) {
        binding.rooms.adapter = NumberAdapter(model.rooms)
    }

    override fun inject(applicationProvider: ApplicationProvider) {
        NumberComponent.init(applicationProvider).inject(this)
    }
}