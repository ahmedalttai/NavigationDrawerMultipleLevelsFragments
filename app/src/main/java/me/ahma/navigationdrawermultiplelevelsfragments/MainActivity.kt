package me.ahma.navigationdrawermultiplelevelsfragments


import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar

import android.view.Gravity
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_drawer.*
import me.ahma.navigationdrawermultiplelevelsfragments.Base.BaseActivity
import me.ahma.navigationdrawermultiplelevelsfragments.Fragments.BookListFragment
import me.ahma.navigationdrawermultiplelevelsfragments.Fragments.SettingsFragment

class MainActivity : BaseActivity() {

    override var drawer: DrawerLayout? = null

    lateinit var toolbar: Toolbar

    lateinit var navigationView: NavigationView

    override lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_drawer)

        toolbar = main_toolbar


        setSupportActionBar(toolbar)

        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        drawer = drawer_layout

        navigationView = navigation_view

        var hView = navigationView.getHeaderView(0)

        drawerToggle = object : ActionBarDrawerToggle(this,drawer,toolbar,0,0) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)

                setDrawerIndicatorEnabled(true)
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }
        }


        //drawerToggle.setHomeAsUpIndicator(R.drawable.ic_launcher_background);
        drawerToggle.setDrawerIndicatorEnabled(false)
        drawer?.addDrawerListener(drawerToggle)


        drawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            drawer?.closeDrawers()

            // Handle navigation view item clicks here.
            when (menuItem.itemId) {

                R.id.nd_bookslist_id -> {
                    showBookListFragment()
                }
                R.id.nd_settings_id -> {
                    showSettingsFragment()
                }

            }
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }

        showBookListFragment()
    }

    //appbar - toolbar button click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer?.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }



    override fun onBackPressed() {
        super.onBackPressed()
        if(drawer!!.isDrawerOpen(Gravity.START)) {
            drawer!!.closeDrawer(Gravity.START)
        }
    }


    fun showBookListFragment() {
        add(BookListFragment.newInstance())
    }


    fun showSettingsFragment() {
        add(SettingsFragment.newInstance())
    }


}
