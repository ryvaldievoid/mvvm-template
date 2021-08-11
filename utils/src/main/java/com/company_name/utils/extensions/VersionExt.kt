package com.company_name.utils.extensions

import android.os.Build

val isAboveHoneyComb
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB

val isAboveKitKat
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

val isAboveLollipop
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

val isAboveM
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

val isAboveN
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N