package com.company_name.utils

interface BindableAdapter<T> {
    fun setRecyclerViewData(data: List<T>)
}