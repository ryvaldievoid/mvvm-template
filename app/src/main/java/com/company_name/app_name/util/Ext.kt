package com.company_name.app_name.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this, ViewModelFactory.getInstance(requireActivity()
        .application)).get(viewModelClass)

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this, ViewModelFactory.getInstance(this.application))
        .get(viewModelClass)

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

@RequiresApi(Build.VERSION_CODES.M)
fun Activity.checkIfAlreadyHavePermission(permission: Array<String>): Boolean {
    for (check in permission) {
        val result: Int = checkSelfPermission(check)
        if (result != PackageManager.PERMISSION_GRANTED) {
            return false
        }
    }
    return true
}

@RequiresApi(Build.VERSION_CODES.M)
fun Fragment.checkIfAlreadyHavePermission(permission: Array<String>): Boolean {
    for (check in permission) {
        val result: Int = checkSelfPermission(this.requireContext(), check)
        if (result != PackageManager.PERMISSION_GRANTED) {
            return false
        }
    }
    return true
}

fun Activity.checkIfGpsEnabled(): Boolean {
    val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
        LocationManager.NETWORK_PROVIDER)
}
