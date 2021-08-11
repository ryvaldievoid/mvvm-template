package com.company_name.utils.extensions

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import java.lang.ref.WeakReference

inline fun <reified T> WeakReference<T>.safe(body: T.() -> Unit) {
    this.get()?.body()
}


/**
 * SpotsDialog general
 */
lateinit var weakRef: WeakReference<Activity>
lateinit var dialog: AlertDialog

fun Context.initProgressDialog(title: String = "Loading...") {
    dialog = AlertDialog
            .Builder(this)
            .create()
            .apply {
                setCancelable(false)
                setCanceledOnTouchOutside(false)
                setTitle(title)
            }
}

@Throws(Exception::class)
fun Activity.safeDialogShow() {
    weakRef = WeakReference(this)
    weakRef.safe {
        val wActivity = this
        // Api >=17
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (::dialog.isInitialized && !dialog.isShowing && !(wActivity).isFinishing && !wActivity.isDestroyed) {
                try {
                    dialog.show()
                } catch (e: Exception) {
                    //Log exception
                    throw Exception(/*getString(R.string.error_initprogress)*/)
                }
            }
        } else {

            // Api < 17. Unfortunately cannot check for isDestroyed()
            if (::dialog.isInitialized && !dialog.isShowing && !(wActivity).isFinishing) {
                try {
                    dialog.show()
                } catch (e: Exception) {
                    //Log exception
                    throw Exception(/*getString(R.string.error_initprogress)*/)
                }
            }
        }

    }
}

@Throws(Exception::class)
fun Activity.safeDialogDismiss() {
    weakRef = WeakReference(this)
    weakRef.safe {
        val wActivity = this
        // Api >=17
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (::dialog.isInitialized && dialog.isShowing && !wActivity.isFinishing && !wActivity.isDestroyed) {
                try {
                    dialog.dismiss()
                } catch (e: Exception) {
                    //Log exception
                    throw Exception(/*getString(R.string.error_initprogress)*/)
                }
            }
        } else {

            // Api < 17. Unfortunately cannot check for isDestroyed()
            if (::dialog.isInitialized && !dialog.isShowing && !(wActivity).isFinishing) {
                try {
                    dialog.dismiss()
                } catch (e: Exception) {
                    //Log exception
                    throw Exception(/*getString(R.string.error_initprogress)*/)
                }
            }
        }
    }
}

fun Activity.showDialogConfirmationUnit(
        msg: String = "",
        todo: () -> Unit
) {
    apply {
        AlertDialog.Builder(this)
                .setTitle("Pesan")
                .setMessage(/*getString(R.string.message_dialog, msg)*/"")
                .setPositiveButton(android.R.string.yes) { dialog, _ ->
                    dialog.apply {
                        cancel()
                        dismiss()
                    }
                    todo.invoke() // execute from outer dialog
                }
                .setNegativeButton(android.R.string.no) { dialog, _ -> dialog.dismiss() }
                .show()
    }
}