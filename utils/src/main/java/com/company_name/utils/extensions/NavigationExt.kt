package com.company_name.utils.extensions

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Safer ways to use requireContext or requireActivity
 */
fun Fragment.safeRequireActivity(): FragmentActivity? = if (isAdded) requireActivity() else activity

fun Fragment.safeRequireContext(): Context? = if (isAdded) requireContext() else context

inline fun <reified T> Context.startActivity(vararg bundlePair: BundlePair, withFlags: Boolean = false) {
//    logEvents(T::class.simpleName, T::class.simpleName)
    startActivity(
            Intent(this, T::class.java).apply {
                if (withFlags)
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtras(bundleOf(*bundlePair))
            }
    )
}

inline fun <reified FRAGMENT : Fragment> FRAGMENT.newInstance(
        featureName: String,
        category: String,
        vararg bundlePair: BundlePair
): FRAGMENT = putArgs {
    putAll(bundleOf(*bundlePair))

    // firebase analytics purpose
//    safeRequireContext()?.logEvents(featureName, category)
}

inline fun <reified FRAGMENT : Fragment> FRAGMENT.newInstance(
        featureName: String,
        category: String,
        vararg bundlePair: BundlePair,
        crossinline custom: (FRAGMENT) -> Unit
): FRAGMENT = putArgs {
    custom.invoke(this@newInstance) // todo custom handler
    putAll(bundleOf(*bundlePair))

    // firebase analytics purpose
//    safeRequireContext()?.logEvents(featureName, category)
}