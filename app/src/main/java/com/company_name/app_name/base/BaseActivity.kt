package com.company_name.app_name.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.company_name.utils.extensions.replaceFragmentInActivity

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    lateinit var mActivity: AppCompatActivity
    lateinit var viewBinding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, bindLayoutRes())
        viewBinding.apply {
            mActivity = this@BaseActivity

            setupViewModel()
            setupToolbar()
            replaceFragment()
            onStartWork()
        }
    }

    private fun setupToolbar() {
        if (bindToolbarId() != null) {
            setSupportActionBar(findViewById(bindToolbarId()!!))
            supportActionBar?.apply {
                setDisplayShowTitleEnabled(true)
            }
        }
    }

    private fun replaceFragment() {
        if (bindFragmentInstance() != null && bindFragmentContainerId() != null) {
            replaceFragmentInActivity(bindFragmentInstance()!!, bindFragmentContainerId()!!)
        }
    }

    @LayoutRes
    abstract fun bindLayoutRes(): Int

    @IdRes
    abstract fun bindToolbarId(): Int?

    @IdRes
    abstract fun bindFragmentContainerId() : Int?

    abstract fun bindFragmentInstance() : Fragment?

    abstract fun onStartWork()

    abstract fun setupViewModel()

}