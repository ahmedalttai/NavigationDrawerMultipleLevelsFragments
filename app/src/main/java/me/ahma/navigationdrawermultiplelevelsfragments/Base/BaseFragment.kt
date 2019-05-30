package me.ahma.navigationdrawermultiplelevelsfragments.Base

import android.os.Bundle
import android.support.v4.app.Fragment

abstract class BaseFragment : Fragment() {

    private var fragmentHandler: AddFragmentHandler? = null

    abstract var title: String

    abstract var backArrow: Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentHandler = AddFragmentHandler(activity!!.supportFragmentManager)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        activity!!.title = title
    }

    protected fun add(fragment: BaseFragment) {
        fragmentHandler!!.add(fragment)
    }
}