package com.company_name.app_name.mvvm.home;

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.company_name.app_name.R
import com.company_name.app_name.base.BaseActivity
import com.company_name.app_name.databinding.HomeActivityBinding
import com.company_name.app_name.util.obtainViewModel


class HomeActivity : BaseActivity<HomeActivityBinding>() {

    lateinit var mViewModel: HomeViewModel

    override fun bindLayoutRes(): Int {
        return R.layout.home_activity
    }

    @Suppress("RedundantNullableReturnType")
    override fun bindToolbarId(): Int? {
        return R.id.toolbar
    }

    @Suppress("RedundantNullableReturnType")
    override fun bindFragmentContainerId(): Int? {
        return R.id.container
    }

    @Suppress("RedundantNullableReturnType")
    override fun bindFragmentInstance(): Fragment? {
        return HomeFragment.newInstance()
    }

    override fun onStartWork() {

    }

    override fun setupViewModel() {
        mViewModel = obtainViewModel(HomeViewModel::class.java).apply {
            start()
        }
        viewBinding.mViewModel = mViewModel
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }

}