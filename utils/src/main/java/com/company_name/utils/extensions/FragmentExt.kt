package com.company_name.utils.extensions

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.company_name.utils.Helper
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

inline fun <FRAGMENT : Fragment> FRAGMENT.putArgs(argsBuilder: Bundle.() -> Unit):
        FRAGMENT = this.apply { arguments = Bundle().apply(argsBuilder) }

inline fun <reified T : AppCompatActivity> Fragment.navigator(

) {
    val intent = Intent(context, T::class.java)
    startActivity(intent)
}

inline fun <reified T : AppCompatActivity> Fragment.navigator(
        param: String
) {
    val intent = Intent(context, T::class.java)
    intent.putExtra("param", param)
    startActivity(intent)
}

fun Fragment.onFinish() {
    activity?.finish()
}

/**
 * @param activityPackage => exp : id.co.gits.feature_home_detail.HomeDetailActivity
 */
fun Fragment.navigatorImplicit(
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

fun Fragment.showToast(
        context: Context,
        message: String
) {
    Toast.makeText(
            context, if (TextUtils.isEmpty(message))
        Helper.Const.SERVER_ERROR_MESSAGE_DEFAULT else message, Toast.LENGTH_SHORT
    ).show()
}

fun Fragment.logD(
        TAG: Fragment,
        message: String
) {
    Log.d(TAG::class.java.simpleName, message)
}

fun Fragment.logV(
        classs: Class<*>,
        message: String
) {
    Log.v(classs::class.java.simpleName, message)
}

fun Fragment.logE(
        classs: Class<*>,
        message: String
) {
    Log.e(classs::class.java.simpleName, message)
}

fun Fragment.saveBitmapToLocalFile(
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

    if (showMessageStatus) this.showToast(context, message)

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


/**
 * Runs a FragmentTransaction, then calls commit().
 */
private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

/**
 * Runs a FragmentTransaction, but check for fragment safety then calls commit().
 * TODO: Set allowStateLoss be true
 */
private inline fun FragmentManager.safeTransact(allowStateLoss: Boolean,
                                                action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
        if (!isStateSaved) commit()
        else if (allowStateLoss) commitAllowingStateLoss()
    }
}

inline fun <reified FRAGMENT : Fragment> AppCompatActivity.setFragmentTag(): String? =
        FRAGMENT::class.java.simpleName

inline fun <reified FRAGMENT : Fragment> AppCompatActivity.getFragmentByTag(): Fragment? =
        supportFragmentManager.findFragmentByTag(FRAGMENT::class.java.simpleName)

// TODO: Build add and hide fragment: https://stackoverflow.com/a/16490344/3763032
fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment,
                                                frameId: Int,
                                                allowStateLoss: Boolean = false) {
    supportFragmentManager.safeTransact(allowStateLoss) {
        replace(frameId, fragment)
    }
}

/**
 * The `fragment` is added to the container view with id `frameId`. The operation is
 * performed by the `fragmentManager`.
 */
fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment,
                                                frameId: Int,
                                                tag: String? = null,
                                                allowStateLoss: Boolean = false,
                                                @AnimRes enterAnimation: Int = 0,
                                                @AnimRes exitAnimation: Int = 0,
                                                @AnimRes popEnterAnimation: Int = 0,
                                                @AnimRes popExitAnimation: Int = 0) {
    supportFragmentManager.safeTransact(allowStateLoss) {
        setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        if (tag == null)
            replace(frameId, fragment)
        else replace(frameId, fragment, tag)
    }
}

fun AppCompatActivity.replaceFragmentWithBackstack(fragment: Fragment,
                                                   frameId: Int,
                                                   allowStateLoss: Boolean = false,
                                                   @AnimRes enterAnimation: Int = 0,
                                                   @AnimRes exitAnimation: Int = 0,
                                                   @AnimRes popEnterAnimation: Int = 0,
                                                   @AnimRes popExitAnimation: Int = 0) {
    supportFragmentManager.safeTransact(allowStateLoss) {
        setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        replace(frameId, fragment)
        addToBackStack(null)
    }
}

fun AppCompatActivity.addFragmentWithBackStack(fragment: Fragment?,
                                               frameId: Int,
                                               tag: String? = null,
                                               allowStateLoss: Boolean = false,
                                               @AnimRes enterAnimation: Int = 0,
                                               @AnimRes exitAnimation: Int = 0,
                                               @AnimRes popEnterAnimation: Int = 0,
                                               @AnimRes popExitAnimation: Int = 0) {
    supportFragmentManager.safeTransact(allowStateLoss) {
        setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        if (tag != null)
            add(frameId, fragment ?: Fragment(), tag)
        else
            add(frameId, fragment ?: Fragment())
        addToBackStack(null)
    }
}

fun Fragment.replaceFragment(fragment: Fragment,
                             frameId: Int,
                             tag: String? = null,
                             allowStateLoss: Boolean = false,
                             @AnimRes enterAnimation: Int = 0,
                             @AnimRes exitAnimation: Int = 0,
                             @AnimRes popEnterAnimation: Int = 0,
                             @AnimRes popExitAnimation: Int = 0) {
    childFragmentManager.safeTransact(allowStateLoss) {
        setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        if (tag != null)
            replace(frameId, fragment, tag)
        else
            replace(frameId, fragment)
    }
}


/**
 * Method to replace the fragment. The [fragment] is added to the container view with id
 * [containerViewId] and a [tag]. The operation is performed by the supportFragmentManager.
 */
fun AppCompatActivity.replaceFragmentSafely(fragment: Fragment,
                                            tag: String,
                                            allowStateLoss: Boolean = false,
                                            @IdRes containerViewId: Int,
                                            @AnimRes enterAnimation: Int = 0,
                                            @AnimRes exitAnimation: Int = 0,
                                            @AnimRes popEnterAnimation: Int = 0,
                                            @AnimRes popExitAnimation: Int = 0) {
    supportFragmentManager.safeTransact(allowStateLoss) {
        setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        replace(containerViewId, fragment, tag)
    }
}

/**
 * For bottomnav
 */
fun AppCompatActivity.hideFragmentSafely(activeFragment: Fragment?,
                                         fragment: Fragment?,
                                         tag: String?,
                                         allowStateLoss: Boolean = false,
                                         @IdRes containerViewId: Int,
                                         @AnimRes enterAnimation: Int = 0,
                                         @AnimRes exitAnimation: Int = 0,
                                         @AnimRes popEnterAnimation: Int = 0,
                                         @AnimRes popExitAnimation: Int = 0) {
    supportFragmentManager.safeTransact(allowStateLoss = allowStateLoss) {
        setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        hide(activeFragment ?: Fragment())
        show(fragment ?: Fragment())
    }
}

/**
 * For bottomnav
 */
fun AppCompatActivity.setupFragmentSafely(fragment: Fragment?,
                                          tag: String?,
                                          allowStateLoss: Boolean = false,
                                          @IdRes containerViewId: Int,
                                          @AnimRes enterAnimation: Int = 0,
                                          @AnimRes exitAnimation: Int = 0,
                                          @AnimRes popEnterAnimation: Int = 0,
                                          @AnimRes popExitAnimation: Int = 0) {
    supportFragmentManager.safeTransact(allowStateLoss = allowStateLoss) {
        setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        add(containerViewId, fragment ?: Fragment(), tag)
    }
}

fun AppCompatActivity.setupHideFragmentSafely(fragment: Fragment?,
                                              tag: String?,
                                              allowStateLoss: Boolean = false,
                                              @IdRes containerViewId: Int,
                                              @AnimRes enterAnimation: Int = 0,
                                              @AnimRes exitAnimation: Int = 0,
                                              @AnimRes popEnterAnimation: Int = 0,
                                              @AnimRes popExitAnimation: Int = 0) {
    supportFragmentManager.safeTransact(allowStateLoss = allowStateLoss) {
        setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        add(containerViewId, fragment ?: Fragment(), tag)
        hide(fragment ?: Fragment())
    }
}


fun AppCompatActivity.popAllBackstackIfExists() {
    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}