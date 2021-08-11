package com.company_name.utils.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

// TODO: Refactor this for better permission request using kotlin idiom
fun Activity.requestPermission(requestCode: Int, permission: String): Boolean {
    return if (isPermissionDenied(permission) && isMarshmallow) {
        ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        false
    } else true
}

val isMarshmallow: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

@SuppressLint("MissingPermission")
fun Activity.isPermissionDenied(permission: String): Boolean =
        ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED

//fun Activity.setPermission(listener: PermissionListener,
//                           isSameThread: Boolean = true,
//                           strPermission: String) {
//    Dexter.withActivity(this)
//            .withPermission(strPermission)
//            .withListener(listener)
//            .apply { if (isSameThread) onSameThread() }
//            .check()
//}
//
//fun Activity.setMultiplePermissions(multiListener: MultiplePermissionsListener,
//                                    isSameThread: Boolean,
//                                    vararg strPermission: String) {
//    Dexter.withActivity(this)
//            .withPermissions(*strPermission)
//            .withListener(multiListener)
//            .apply { if (isSameThread) onSameThread() }
//            .check()
//}
