package com.company_name.utils.extensions

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

inline fun <reified T : AppCompatActivity> Context.navigator(

) {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

inline fun <reified T : AppCompatActivity> Context.navigator(
        param: String
) {
    val intent = Intent(this, T::class.java)
    intent.putExtra("param", param)
    startActivity(intent)
}

fun Context.showToast(
        message: String
) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.logD(
        classs: Class<*>,
        message: String
) {
    Log.d(classs::class.java.simpleName, message)
}

fun Context.logV(
        classs: Class<*>,
        message: String
) {
    Log.v(classs::class.java.simpleName, message)
}

fun Context.logE(
        classs: Class<*>,
        message: String
) {
    Log.e(classs::class.java.simpleName, message)
}