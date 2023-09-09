package com.example.number.di.module

import com.example.number.data.api.NumberApi
import com.example.number.data.repository.NumberRepository
import dagger.Module
import dagger.Provides

@Module
class NumberRepositoryModule {

    @Provides
    fun provideNumberRepository(api: NumberApi) = NumberRepository(api)
}