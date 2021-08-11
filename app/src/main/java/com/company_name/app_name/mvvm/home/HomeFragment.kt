package com.company_name.app_name.mvvm.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company_name.app_name.base.BaseFragment
import com.company_name.app_name.databinding.HomeFragmentBinding
import com.company_name.app_name.util.obtainViewModel


class HomeFragment : BaseFragment() {

    private lateinit var mViewDataBinding: HomeFragmentBinding
    lateinit var mViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mViewDataBinding = HomeFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@HomeFragment
        }
        mViewModel = (activity as HomeActivity).obtainViewModel(HomeViewModel::class.java)

        mViewDataBinding.mViewModel = mViewModel.apply {

        }

//        mViewDataBinding.mListener = object : HomeFragmentUserActionListener {
//
//        }

        return mViewDataBinding.root
    }

    override fun setContentData() {
        mViewDataBinding.title.text = "Tes Fragment"
        showMessage("Hello")
    }

    override fun setMessageType(): String {
        return MESSAGE_TYPE_SNACK
    }

    companion object {

        fun newInstance() = HomeFragment().apply {

        }

    }

}