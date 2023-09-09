package com.example.number.di.module

import androidx.lifecycle.ViewModel
import com.example.core.di.viewmodel.ViewModelKey
import com.example.number.presentation.viewmodel.NumberViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface NumberViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(NumberViewModel::class)
    fun provideNumberViewModel(viewModel: NumberViewModel): ViewModel
}