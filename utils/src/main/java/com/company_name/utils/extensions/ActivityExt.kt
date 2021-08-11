package com.company_name.utils.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.company_name.utils.Helper
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

fun AppCompatActivity.replaceFragmentInActivity(
    fragment: Fragment,
    frameId: Int
) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}

fun AppCompatActivity.replaceFragmentInActivityWithBackStack(
    fragment: Fragment,
    frameId: Int,
    TAG: String?
) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
        addToBackStack(TAG)
    }
}

fun AppCompatActivity.showToast(
    context: Context,
    message: String
) {
    Toast.makeText(
        context, if (TextUtils.isEmpty(message))
            Helper.Const.SERVER_ERROR_MESSAGE_DEFAULT else message, Toast.LENGTH_SHORT
    ).show()
}

private inline fun FragmentManager.transact(
    action: FragmentTransaction.() -> Unit
) {
    beginTransaction().apply {
        action()
    }.commit()
}

@SuppressLint("ObsoleteSdkInt")
fun AppCompatActivity.transparentStatusBar(
    decorView: View
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
}

inline fun <reified T : AppCompatActivity> AppCompatActivity.navigator(

) {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

inline fun <reified T : AppCompatActivity> AppCompatActivity.navigator(
    param: String
) {
    val intent = Intent(this, T::class.java)
    intent.putExtra("param", param)
    startActivity(intent)
}

/**
 * @param activityPackage => exp : id.co.gits.feature_home_detail.HomeDetailActivity
 */
fun AppCompatActivity.navigatorImplicit(
    context: Context,
    activityPackage: String
) {
    val intent = Intent()
    try {
        intent.setClass(
            context,
            Class.forName(activityPackage)
        )
        startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun AppCompatActivity.logD(
    classs: Class<*>,
    message: String
) {
    Log.d(classs::class.java.simpleName, message)
}

fun AppCompatActivity.logV(
    classs: Class<*>,
    message: String
) {
    Log.v(classs::class.java.simpleName, message)
}

fun AppCompatActivity.logE(
    classs: Class<*>,
    message: String
) {
    Log.e(classs::class.java.simpleName, message)
}

fun AppCompatActivity.saveBitmapToLocalFile(
    context: Context,
    imageBitmap: Bitmap,
    directoryName: String?,
    showMessageStatus: Boolean
) {
    val root = Environment.getExternalStorageDirectory().toString()

    val directoryNameDefault = if (TextUtils.isEmpty(directoryName)) {
        Helper.Const.APP_FOLDER_DEFAULT
    } else {
        directoryName
    }

    val myDir = File("$root/$directoryNameDefault")

    if (!myDir.exists()) {
        myDir.mkdirs()
    }

    val random = Random()
    val numbers = 100
    val numberResult = random.nextInt(numbers)
    val imageFileName = "IMG_$numberResult.png"
    val existImageFile = File(myDir, imageFileName)
    val outStream: FileOutputStream
    val bitmap = imageBitmap
    var isSuccessFileSaving: Boolean

    try {
        outStream = FileOutputStream(existImageFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream)
        /* 100 to keep full quality of the image */
        outStream.flush()
        outStream.close()
        isSuccessFileSaving = true
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
        isSuccessFileSaving = false
    } catch (e: IOException) {
        e.printStackTrace()
        isSuccessFileSaving = false
    }

    val message = if (isSuccessFileSaving) {
        Helper.Const.MESSAGE_SUCCESS_IMAGE_SAVE
    } else {
        Helper.Const.MESSAGE_FAILED_IMAGE_SAVE
    }

    if (showMessageStatus) showToast(context, message)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        val scanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val contentUri = Uri.fromFile(existImageFile)
        scanIntent.data = contentUri
        context.sendBroadcast(scanIntent)
    } else {
        context.sendBroadcast(
            Intent(
                Intent.ACTION_MEDIA_MOUNTED,
                Uri.parse(
                    Helper.Const.SDCARD_URI_PATH + Environment
                        .getExternalStorageDirectory()
                )
            )
        )
    }
}