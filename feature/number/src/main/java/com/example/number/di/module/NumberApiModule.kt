package com.example.number.di.module

import com.example.number.data.api.NumberApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NumberApiModule {

    @Provides
    fun provideNumberApi(retrofit: Retrofit): NumberApi {
        return retrofit.create(NumberApi::class.java)
    }
}