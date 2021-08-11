package com.company_name.utils.extensions

import android.content.Intent
import androidx.fragment.app.Fragment
import java.io.File

const val REQUEST_IMAGE = 555

//suspend fun Fragment.downloadFile(url: String?): Uri? =
//        getFileFromUrlChannel(safeRequireContext(), url).receive()

/*fun Context.getUriForFileProvider(uriPath: String): Uri =
        getUriForFile(this, "${BuildConfig.APPLICATION_ID}.provider", File(uriPath))*/


fun Fragment.openGallery() {
    Intent().apply {
        action = Intent.ACTION_GET_CONTENT
        type = "image/*"

//        startActivityForResult(Intent.createChooser(this, "Select Picture"), id.co.gits.gitsutils.extensions.REQUEST_IMAGE)
    }
}

/*
fun Context.getByteArrayFromUri(uri: Uri?): ByteArray {
    val bitmap = getBitmapFromUri(uri)
    val stream = ByteArrayOutputStream()

    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    bitmap.recycle()

    return stream.toByteArray()
}
*/

// Get length of file in bytes
val File.fileSizeInBytes: Long
    get() = length()

// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
val File.fileSizeInKB: Long
    get() = fileSizeInBytes / 1024
// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
val File.fileSizeInMB: Long
    get() = fileSizeInKB / 1024

// Get length of file in bytes
val ByteArray.fileSizeInBytes: Long
    get() = size.toLong()

// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
val ByteArray.fileSizeInKB: Long
    get() = fileSizeInBytes / 1024
// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
val ByteArray.fileSizeInMB: Long
    get() = fileSizeInKB / 1024