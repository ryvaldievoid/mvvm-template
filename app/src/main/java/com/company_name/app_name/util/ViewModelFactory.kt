package com.company_name.app_name.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.company_name.app_name.mvvm.home.HomeViewModel
import com.company_name.app_name.mvvm.splashscreen.SplashscreenViewModel

class ViewModelFactory private constructor(
    private val mApplication: android.app.Application
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(SplashscreenViewModel::class.java) ->
                    SplashscreenViewModel(mApplication)
                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(mApplication)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {

        @Volatile private var INSTANCE: ViewModelFactory? = null
        fun getInstance(mApplication: android.app.Application) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(mApplication)
                    .also { INSTANCE = it }
            }
    }
}