package com.example.hotel.di.module

import androidx.lifecycle.ViewModel
import com.example.core.di.viewmodel.ViewModelKey
import com.example.hotel.presentation.viewmodel.HotelViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HotelViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(HotelViewModel::class)
    fun provideHotelViewModel(viewModel: HotelViewModel): ViewModel
}