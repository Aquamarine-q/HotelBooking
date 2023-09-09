package com.example.booking.di.module

import androidx.lifecycle.ViewModel
import com.example.core.di.viewmodel.ViewModelKey
import com.example.booking.presentation.viewmodel.BookingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface BookingViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(BookingViewModel::class)
    fun provideBookingViewModel(viewModel: BookingViewModel): ViewModel
}