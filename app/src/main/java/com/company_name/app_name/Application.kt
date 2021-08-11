package com.company_name.app_name

import android.app.Application
import android.content.Context
import com.company_name.utils.Helper

/**
 * Created by irfanirawansukirman on 26/01/18.
 */
class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(Helper.SystemLocale.onAttach(base, "en"))
    }

    companion object {
        lateinit var instance: com.company_name.app_name.Application

        fun getContext(): Context = instance.applicationContext
    }

}