package com.company_name.app_name.util.chocohelper

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.company_name.app_name.base.BaseFragment
import com.company_name.app_name.base.BaseUserActionListener
import com.company_name.app_name.base.BaseViewModel
import com.company_name.app_name.data.source.DataSource
import com.company_name.app_name.data.source.Repository
import com.company_name.app_name.data.source.local.LocalDataSource
import com.company_name.app_name.data.source.remote.RemoteDataSource
import com.company_name.app_name.util.obtainViewModel

object ChocoChips {

    private var localDataSource: DataSource? = null
    private var remoteDataSource: DataSource? = null

    @JvmStatic
    fun inject(vm: BaseViewModel) {
        val myClass = vm::class.java
        for (field in myClass.superclass?.declaredFields?: emptyArray()) {
            val annotation = field.getAnnotation(ChocoRepository::class.java)
            if (annotation != null) {
                if (localDataSource == null && remoteDataSource == null) {
                    localDataSource = LocalDataSource(vm.getApplication())
                    remoteDataSource = RemoteDataSource
                }

                field.isAccessible = true
                field.set(vm, Repository(remoteDataSource!!, localDataSource!!))
                field.isAccessible = false
            }
        }
    }

//    @JvmStatic
//    fun <T : ViewDataBinding, VM : BaseViewModel, L : BaseUserActionListener> inject(fragment: BaseFragment<VM>) {
//        var mBinding: ViewDataBinding? = null
//        var mVM: ViewModel? = null
//
//        val myClass = fragment::class.java
//        for (field in myClass.declaredFields) {
//            val annotation = field.getAnnotation(ChocoBinding::class.java)
//            if (annotation != null) {
//                mBinding = DataBindingUtil.inflate<T>(LayoutInflater.from(fragment.requireActivity()), annotation.layout, null, false)
//                field.isAccessible = true
//                field.set(fragment, mBinding)
//                field.isAccessible = false
//            }
//
//            val vmAnnotation = field.getAnnotation(ChocoViewModel::class.java)
//            if (vmAnnotation != null) {
//                if (ViewModel::class.java.isAssignableFrom(field.type)) {
//                    mVM = fragment.obtainViewModel(field.type as Class<VM>)
//                    field.isAccessible = true
//                    field.set(fragment, mVM)
//                    field.isAccessible = false
//                    fragment.mParentVM = mVM
//                }
//            }
//        }
//
//        if (mBinding != null && mVM != null) {
//            val classBinding = mBinding::class.java
//            for (bindingField in classBinding.declaredFields) {
//                if (ViewModel::class.java.isAssignableFrom(bindingField.type)) {
//                    bindingField.isAccessible = true
//                    bindingField.set(mBinding, mVM)
//                    bindingField.isAccessible = false
//                } else if (BaseUserActionListener::class.java.isAssignableFrom(bindingField.type) && BaseUserActionListener::class.java.isAssignableFrom(fragment::class.java)){
//                    bindingField.isAccessible = true
//                    bindingField.set(mBinding, fragment as L)
//                    bindingField.isAccessible = false
//                }
//            }
//        }
//    }

}