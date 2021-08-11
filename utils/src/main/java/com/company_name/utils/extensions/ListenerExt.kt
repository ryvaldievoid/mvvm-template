package com.company_name.utils.extensions

// TODO: Build custom listener
/*
fun Context.myBottomSheetListener(todo: () -> Bundle): MyBottomsheetListener = with(this) ctx@{
    val bundle = todo.invoke()
    object : MyBottomsheetListener {
        override fun onHomeClicked() {
            this@ctx.startActivity<HomeActivity>()
        }

        override fun onPustakaClicked() {
            startActivity<ForumActivity>("" bundleTo "")
        }

        override fun onElearningClicked() {
        }

        override fun onEdukasiClicked() {
        }

        override fun onTanyaAhliClicked() {
        }

        override fun onToolsClicked() {
        }

        override fun onForumClicked() {
        }
    }
}

private val mBottomNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->

    // Add the new tab fragment
    var fragment: Fragment? = null
    var tag: String? = null
    var bottomNavPosition: HomeActivity.BottomNavPosition = HomeActivity.BottomNavPosition.OTHER

    when (menuItem.itemId) {
        R.id.home -> {
            fragment = HomeFragment.newInstance()
            tag = HomeFragment::class.java.simpleName
            bottomNavPosition = HomeActivity.BottomNavPosition.HOME
        }
        R.id.pustaka -> {
            fragment = PustakaParentFragment.newInstance()
            tag = PustakaParentFragment::class.java.simpleName
            bottomNavPosition = HomeActivity.BottomNavPosition.PUSTAKA
        }
        R.id.alat_bantu -> {
            toastSpammable("Tools Bidan")
        }
        R.id.epoin -> {
            toastSpammable("E-Poin")
        }
        R.id.akun -> {

        }
    }

    if (fragment != null && tag != null) {

        // If user click menu that isn't home, renew the handler
        if (mBottomNavPosition != HomeActivity.BottomNavPosition.HOME) {
            renewHandlerBack()
        }
        replaceFragment(fragment, tag, bottomNavPosition)
    }

    return@OnNavigationItemSelectedListener true
}
*/
