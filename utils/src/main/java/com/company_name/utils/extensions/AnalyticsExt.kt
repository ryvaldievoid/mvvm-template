package com.company_name.utils.extensions

//fun Context.logEvents(
//        featureName: String?,
//        category: String?
//) {
//    firebaseAnalytics.logEvent(
//            FirebaseAnalytics.Event.VIEW_ITEM,
//            bundleOf(
//                    // TODO: change the user implementation
//                    "user_id" bundleTo (prefs[""] ?: ""),
//                    FirebaseAnalytics.Param.ITEM_NAME bundleTo (featureName ?: ""),
//                    FirebaseAnalytics.Param.ITEM_CATEGORY bundleTo (category ?: "")
//            )
//    )
//}
//
//fun Context.calculatePoint(
//        featureName: String?,
//        point: Int?
//) {
//    firebaseAnalytics.logEvent(
//            FirebaseAnalytics.Event.VIEW_ITEM,
//            bundleOf(
//                    // TODO: change the user implementation
//                    "user_id" bundleTo (prefs[""] ?: ""),
//                    FirebaseAnalytics.Param.ITEM_NAME bundleTo (featureName ?: ""),
//                    FirebaseAnalytics.Param.ITEM_CATEGORY bundleTo (point ?: 0)
//            )
//    )
//}