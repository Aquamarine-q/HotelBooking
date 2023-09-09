package com.example.core.di

import com.example.core.di.provider.ApplicationProvider

interface App {

    fun getApplicationProvider(): ApplicationProvider
}