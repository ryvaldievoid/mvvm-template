package com.company_name.utils

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Handler
import androidx.appcompat.app.AlertDialog

@Suppress("MemberVisibilityCanBePrivate")
class DialogUtil(private val context: Context) {

    private var progressDialog: ProgressDialog = ProgressDialog(context)

    fun openProgressDialog(
        message: String?,
        title: String?,
        cancelOutsideDialog: Boolean
    ): ProgressDialog {
        return progressDialog.apply {
            setTitle(title ?: context.getString(R.string.app_name))
            setMessage(message ?: context.getString(R.string.loading))
            setCanceledOnTouchOutside(cancelOutsideDialog)
        }
    }

    fun closeProgressDialog() {
        Handler().postDelayed({
            openProgressDialog("", "", false).dismiss()
        }, 1000)
    }

    fun openAlertDialog(
        message: String?,
        title: String?,
        cancelOutsideDialog: Boolean,
        positiveTitle: String,
        negativeTitle: String,
        alertDialogCallback: AlertCallbackDialog
    ): Dialog {
        return AlertDialog
            .Builder(context)
            .setTitle(title ?: context.getString(R.string.app_name))
            .setMessage(message ?: context.getString(R.string.loading))
            .setCancelable(cancelOutsideDialog)
            .setPositiveButton(positiveTitle) { dialog, which ->
                alertDialogCallback.onPositiveDialogClicked(
                    dialogInterface = dialog,
                    which = which
                )
            }
            .setNegativeButton(negativeTitle) { dialog, which ->
                alertDialogCallback.onNegativeDialogClicked(
                    dialogInterface = dialog,
                    which = which
                )
            }
            .create()
    }

    /**
     * @see: id.co.gits.gitsutils.extensions#Activty.showDialogConfirmationUnit()
     */
    interface AlertCallbackDialog {
        fun onPositiveDialogClicked(dialogInterface: DialogInterface?, which: Int)
        fun onNegativeDialogClicked(dialogInterface: DialogInterface?, which: Int)
    }

}
