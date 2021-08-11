package com.company_name.utils.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.company_name.utils.HashUtils
import com.company_name.utils.StringEncryptionTools
import java.lang.reflect.Type

// TODO: Change this to use Kotlin serialization instead
val gson by lazy { Gson() }

inline fun <reified T> makeType() = object : TypeToken<T>() {}.type

inline fun <reified T> makeTypeInline() = object : TypeToken<T>() {}.type

fun <T> T.toJson(): String = gson.toJson(this)

inline fun <reified T> String.fromJson(): T = gson.fromJson(this, makeType<T>())

inline fun <reified T> String.fromJsonInline(): T {
    return gson.fromJson(this, makeTypeInline<T>())
}

fun String.encryptText() = StringEncryptionTools().encryptText(this) ?: ""

fun String.decryptText() = StringEncryptionTools().decryptText(this) ?: ""

fun String.sha512() = HashUtils.hashString("SHA-512", this)

fun String.sha256() = HashUtils.hashString("SHA-256", this)

fun String.sha1() = HashUtils.hashString("SHA-1", this)

//fun String.hasBearerAuthTokenPrefix(): Boolean {
//    return this.startsWith(httpHeaderBearerTokenPrefix)
//}
//
//fun String.withBearerAuthTokenPrefix(): String {
//    return when (this.hasBearerAuthTokenPrefix()) {
//        false -> "$httpHeaderBearerTokenPrefix $this"
//        true -> this
//    }
//}
//
//fun String.trimmingBearerAuthTokenPrefix(): String {
//    var trimmed = this
//    // while it starts with the prefix:
//    while (trimmed.hasBearerAuthTokenPrefix()) {
//        // trim off a prefix!
//        trimmed = trimmed.substring(httpHeaderBearerTokenPrefix.length)
//    }
//    return trimmed
//}


// JAVA interop failed
object JSON {
    @JvmField
    val gson = Gson()

    @JvmStatic
    fun <OBJ> fromJson(str: String, type: Type): OBJ = gson.fromJson<OBJ>(str, type)

    @JvmStatic
    fun <OBJ> toJson(obj: OBJ): String = gson.toJson(obj)

    @JvmStatic
    fun <TYPE> makeType(): Type = object : TypeToken<TYPE>() {}.type
}