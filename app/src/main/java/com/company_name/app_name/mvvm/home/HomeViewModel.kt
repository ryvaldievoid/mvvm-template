package com.company_name.app_name.mvvm.home;

import android.app.Application
import android.util.Log
import com.company_name.app_name.base.BaseViewModel
import com.company_name.app_name.util.chocohelper.ChocoChips

class HomeViewModel(context: Application) : BaseViewModel(context) {

    override fun start() {
        super.start()
        ChocoChips.inject(this)
    }

    override fun onClearDisposable() {
        super.onClearDisposable()
        repository.onClearDisposables()
    }

}