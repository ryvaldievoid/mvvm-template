package com.company_name.utils.extensions

import android.content.Context
import android.net.Uri
import com.company_name.utils.FileUtils
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.net.URI

val MEDIA_TYPE_TEXT = MediaType.parse("text/plain")
val MEDIA_TYPE_IMAGE = MediaType.parse("image/*")
val MEDIA_TYPE_BINARY = MediaType.parse("application/octet-stream")

fun String?.toRequestBody(type: MediaType? = MEDIA_TYPE_TEXT): RequestBody =
        RequestBody.create(type, this ?: "")

fun ByteArray?.toRequestBody(type: MediaType? = MEDIA_TYPE_BINARY): RequestBody =
        RequestBody.create(type, this ?: byteArrayOf(0))

fun File?.toRequestBody(type: MediaType? = MEDIA_TYPE_IMAGE): RequestBody =
        RequestBody.create(type, this ?: File(URI.create(Uri.EMPTY.path)))

fun RequestBody.toMultipartBody(partName: String = "image", file: File?): MultipartBody.Part =
        MultipartBody.Part.createFormData(partName, file?.name, this)

fun Context.prepareFilePart(partName: String = "image", fileUri: Uri): MultipartBody.Part {
    // create RequestBody instance from file
    val file = FileUtils.getFile(this, fileUri)
    val requestFile = RequestBody.create(
            MediaType.parse(
                    this.contentResolver.getType(fileUri) ?: "image/*"),
            file ?: File(URI.create(Uri.EMPTY.path)))

    // MultipartBody.Part is used to send also the actual this name
    return requestFile.toMultipartBody(file?.name ?: "", file)
}

// TODO: Build this using pattern like BundlePair class
/*
@UseExperimental(ImplicitReflectionSerializer::class)
fun ImageUploadParam.toPartMap(ctx: Context): Map<String, RequestBody> {
    val res: HashMap<String, RequestBody> = hashMapOf()

    Mapper.map(this)
            .filterKeys { it != "isEmpty" }
            .forEach { (t, u) ->
                if (t == "file") {
                    val file = getFile(ctx, Uri.parse("$u"))
                    // TODO: Build a ByteArray instead
                    res["$t\"; filename=\"image.jpg\" "] = file.toRequestBody()
                } else {
                    res[t] = "$u".toRequestBody()
                }
            }

    return res
}

@UseExperimental(ImplicitReflectionSerializer::class)
fun CitiesParam.toQueryMap(): Map<String, String> {
    val res: HashMap<String, String> = hashMapOf()

    Mapper.map(this)
            .filterKeys { it != "isEmpty" }
            .forEach { (t, u) ->
                res[t] = "$u"
            }

    return res
}

// TODO: Build like BundleExt.kt for readable code and SOLID principles
@Suppress("UNCHECKED_CAST")
inline fun <reified T : Any?> T?.anyToRequestBody(): RequestBody? =
        when (T::class) {
            ByteArray::class ->
                (this as ByteArray).toRequestBody()
            File::class ->
                if (this is File? && this != null) toRequestBody()
                else this.toString().toRequestBody()
            else ->
                this.toString().toRequestBody()
        }*/
