package com.example.bookhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.bookhub.toolbar
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar as toolbar1

lateinit var drawerLayout: DrawerLayout
lateinit var coordinatorLayout: CoordinatorLayout
lateinit var toolbar  : Toolbar
lateinit var frameLayout: FrameLayout
lateinit var navigationView: NavigationView
var prev : MenuItem? = null


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer)
        coordinatorLayout = findViewById(R.id.coordinator)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.navigation)
        setupToolbar()
        openDashboard()
        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, R.string.open,R.string.close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener {
            if (prev != null){
                prev?.isChecked = false
            }
            it.isCheckable=true
            it.isChecked=true
            prev=it
            when(it.itemId)
            {
                R.id.dashboard -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,DashboardFragment())
                        .addToBackStack("Dashboard")
                        .commit()
                    supportActionBar?.title="Dashboard"
                    drawerLayout.closeDrawers()

                }

                R.id.favourites -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,FavouritesFragment())
                        .addToBackStack("Favourites")
                        .commit()
                    supportActionBar?.title="Favourites"
                    drawerLayout.closeDrawers()
                }

                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,ProfileFragment())
                        .addToBackStack("Profile")
                        .commit()
                    supportActionBar?.title="Profile"
                    drawerLayout.closeDrawers()
                }

                R.id.about -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,AboutFragment())
                        .addToBackStack("About")
                        .commit()
                    supportActionBar?.title="About App"
                    drawerLayout.closeDrawers()
                }

            }
            return@setNavigationItemSelectedListener true
        }
    }
    fun setupToolbar(){
        setSupportActionBar(toolbar!!)
        supportActionBar?.title="Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
    fun openDashboard(){
        val fragment = DashboardFragment()
        val trans= supportFragmentManager.beginTransaction()
        trans.replace(R.id.frame,fragment)
        trans.commit()
        supportActionBar?.title="Dashboard"
        navigationView.setCheckedItem(R.id.dashboard)
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frame)
        when(frag){
            !is DashboardFragment -> openDashboard()
            else -> super.onBackPressed()
        }
    }
}