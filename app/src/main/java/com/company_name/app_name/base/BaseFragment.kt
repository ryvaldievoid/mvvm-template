package com.company_name.app_name.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.company_name.app_name.R
import com.company_name.utils.extensions.showSnackbarDefault
import com.company_name.utils.extensions.showSnackbarWithCustomColor
import com.company_name.utils.extensions.showToast

abstract class BaseFragment : Fragment() {

    private var mMessageType = MESSAGE_TYPE_SNACK

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mMessageType = setMessageType()
        setContentData()
    }

    abstract fun setContentData()
    abstract fun setMessageType(): String
    fun showMessage(it: String) {
        when (mMessageType) {
            MESSAGE_TYPE_SNACK_CUSTOM -> {
                view?.showSnackbarWithCustomColor(it,
                    R.color.colorAccent,
                    R.color.greyBackgroundDefault)
            }
            MESSAGE_TYPE_SNACK -> {
                view?.showSnackbarDefault(it)
            }
            MESSAGE_TYPE_TOAST -> {
                requireContext().showToast(it)
            }
        }
    }

    companion object {
        const val MESSAGE_TYPE_TOAST = "TOAST_TYPE"
        const val MESSAGE_TYPE_SNACK = "SNACK_TYPE"
        const val MESSAGE_TYPE_SNACK_CUSTOM = "SNACK_CUSTOM_TYPE"
    }

}