package com.company_name.app_name.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.databinding.ObservableField
import android.util.Log
import com.company_name.app_name.data.source.Repository
import com.company_name.utils.NavigationParamGlobal
import com.company_name.app_name.util.SingleLiveEvent
import com.company_name.app_name.util.chocohelper.ChocoRepository

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    var eventShowProgress = SingleLiveEvent<Boolean>()
    var eventGlobalMessage = SingleLiveEvent<String>()
    var eventNavigationPage = SingleLiveEvent<NavigationParamGlobal>()

    var verticalList = ObservableField(0)
    var horizontalList = ObservableField(1)

    fun showLogDebug(TAG: String, data: String) = Log.d(TAG, data)
    fun showLogVerbose(TAG: String, data: String) = Log.v(TAG, data)
    fun showLogError(TAG: String, errorMessage: String) = Log.e(TAG, errorMessage)


    @ChocoRepository
    lateinit var repository: Repository

    open fun start() {}
    open fun onClearDisposable() {}

}