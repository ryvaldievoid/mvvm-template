package com.company_name.app_name.mvvm.splashscreen

import android.os.Handler
import androidx.fragment.app.Fragment
import com.company_name.app_name.R
import com.company_name.app_name.base.BaseActivity
import com.company_name.app_name.databinding.SplashscreenActivityBinding
import com.company_name.app_name.mvvm.home.HomeActivity
import com.company_name.app_name.util.obtainViewModel

class SplashscreenActivity : BaseActivity<SplashscreenActivityBinding>() {

    lateinit var mViewModel: SplashscreenViewModel

    override fun bindLayoutRes(): Int {
        return R.layout.splashscreen_activity
    }

    @Suppress("RedundantNullableReturnType")
    override fun bindToolbarId(): Int? {
        return null
    }

    @Suppress("RedundantNullableReturnType")
    override fun bindFragmentContainerId(): Int? {
        return null
    }

    @Suppress("RedundantNullableReturnType")
    override fun bindFragmentInstance(): Fragment? {
        return null
    }

    override fun onStartWork() {
        mViewModel.checkFunc()
        Handler().postDelayed({
            HomeActivity.startActivity(this)
            finish()
        }, 2000)
    }

    override fun setupViewModel() {
        mViewModel = obtainViewModel(SplashscreenViewModel::class.java).apply {
            start()
        }
        viewBinding.mViewModel = mViewModel
    }

}
