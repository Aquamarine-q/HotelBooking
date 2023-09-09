package com.example.core.di.provider

import retrofit2.Retrofit

interface NetworkProvider {

    fun provideRetrofit(): Retrofit
}