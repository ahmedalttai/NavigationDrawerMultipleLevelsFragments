package me.ahma.navigationdrawermultiplelevelsfragments.Base

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View

abstract class BaseActivity : AppCompatActivity() {

    private var fragmentManager: FragmentManager? = null
    private var fragmentHandler: AddFragmentHandler? = null

    var pref: SharedPreferences? = null

    internal val navigationBackPressListener: View.OnClickListener =
        View.OnClickListener { fragmentManager!!.popBackStack() }
    internal var backStackListener: FragmentManager.OnBackStackChangedListener =
        FragmentManager.OnBackStackChangedListener { onBackStackChangedEvent() }

    protected abstract val drawerToggle: ActionBarDrawerToggle?

    protected abstract var drawer: DrawerLayout?

    private fun onBackStackChangedEvent() {
        syncDrawerToggleState()
    }

    private fun syncDrawerToggleState() {
        val drawerToggle = drawerToggle ?: return
        if (fragmentManager!!.backStackEntryCount > 1) {

                    drawerToggle.isDrawerIndicatorEnabled = false
                    //drawerToggle.setHomeAsUpIndicator(R.drawable.ic_launcher_background)
                    drawerToggle.toolbarNavigationClickListener = navigationBackPressListener //pop backstack


        } else {
            drawerToggle.isDrawerIndicatorEnabled = true

//            drawerToggle.setHomeAsUpIndicator(R.drawable.ic_launcher_background)
            drawerToggle.toolbarNavigationClickListener =
                drawerToggle.toolbarNavigationClickListener //open nav menu drawer
        }
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentManager = supportFragmentManager

        fragmentHandler = AddFragmentHandler(fragmentManager ?: return)

        fragmentManager!!.addOnBackStackChangedListener(backStackListener)

        pref = applicationContext.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        drawer!!.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

            }

            override fun onDrawerOpened(drawerView: View) {
                syncDrawerToggleState()
            }

            override fun onDrawerClosed(drawerView: View) {
                syncDrawerToggleState()
            }

            override fun onDrawerStateChanged(newState: Int) {

            }
        })
    }

    override fun onDestroy() {
        fragmentManager!!.removeOnBackStackChangedListener(backStackListener)
        fragmentManager = null
        super.onDestroy()
    }

    protected fun add(fragment: BaseFragment) {
        fragmentHandler!!.add(fragment)
    }

    override fun onBackPressed() {
        if (sendBackPressToDrawer()) {
            //the drawer consumed the backpress
            return
        }

        if (sendBackPressToFragmentOnTop()) {
            // fragment on top consumed the back press
            return
        }

        //let the android system handle the back press, usually by popping the fragment
        super.onBackPressed()

        //close the activity if back is pressed on the root fragment
        if (fragmentManager!!.backStackEntryCount == 0) {
            finish()
        }
    }

    private fun sendBackPressToDrawer(): Boolean {
        val drawer = drawer
        if (drawer != null && drawer.isDrawerOpen(Gravity.RIGHT)) {
            drawer.closeDrawer(Gravity.RIGHT)
            return true
        }
        return false
    }

    private fun sendBackPressToFragmentOnTop(): Boolean {
        var fragmentOnTop = fragmentHandler!!.currentFragment

        if (fragmentOnTop == null) {
            return false
        }
        if (!(fragmentOnTop is BackButtonSupportFragment)) {
            return false
        }
        var consumedBackPress = fragmentOnTop as BackButtonSupportFragment
        consumedBackPress.onBackPressed()
        return consumedBackPress as Boolean
//        val fragmentOnTop =
//            (fragmentHandler!!.currentFragment ?: return false) as? BackButtonSupportFragment ?: return false
//        return fragmentOnTop.onBackPressed()
    }
}