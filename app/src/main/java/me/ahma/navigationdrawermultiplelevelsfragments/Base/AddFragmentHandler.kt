package me.ahma.navigationdrawermultiplelevelsfragments.Base

import android.support.v4.app.FragmentManager
import android.util.Log
import me.ahma.navigationdrawermultiplelevelsfragments.R

class AddFragmentHandler(private val fragmentManager: FragmentManager) {

    val currentFragment: BaseFragment?
        get() {
            if (fragmentManager.backStackEntryCount == 0) {
                return null
            }
            val currentEntry = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1)

            val tag = currentEntry.name
            val fragment = fragmentManager.findFragmentByTag(tag)
            return fragment as BaseFragment?
        }

    fun add(fragment: BaseFragment) {
        //don't add a fragment of the same type on top of itself.
        val currentFragment = currentFragment

        if (currentFragment != null) {

            if (currentFragment.title === "Books List") {

            } else {
                if (currentFragment.javaClass === fragment.javaClass) {
                    Log.w(
                        "Fragment Manager",
                        "Tried to add a fragment of the same type to the backstack. This may be done on purpose in some circumstances but generally should be avoided."
                    )
                    return
                }
            }
        }

        if (fragmentManager.backStackEntryCount == 0) {

            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.main_content,fragment,fragment.title)
            fragmentTransaction.addToBackStack(fragment.title)

            fragmentTransaction.commit()

        } else {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main_content, fragment, fragment.title)

            if (fragment.backArrow) {
                fragmentTransaction.addToBackStack(fragment.title)
            }
            fragmentTransaction.commit()
        }


    }
}