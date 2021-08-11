package com.company_name.app_name.base

interface BaseDataSource {
    interface GitsResponseCallback<T> {
        fun onShowProgressDialog()
        fun onHideProgressDialog()
        fun onSuccess(data: T)
        fun onFinish()
        fun onFailed(statusCode: Int, errorMessage: String? = "")
    }

    fun onClearDisposables()
}