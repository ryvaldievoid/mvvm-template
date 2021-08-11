package com.company_name.app_name.mvvm.splashscreen;

import android.app.Application
import android.util.Log
import com.company_name.app_name.base.BaseViewModel
import com.company_name.app_name.util.chocohelper.ChocoChips

class SplashscreenViewModel(context: Application) : BaseViewModel(context) {

    fun checkFunc() {
        Log.i("tes view model", "ta da")
    }

    override fun start() {
        super.start()
        ChocoChips.inject(this)
    }

    override fun onClearDisposable() {
        super.onClearDisposable()
        repository.onClearDisposables()
    }

}