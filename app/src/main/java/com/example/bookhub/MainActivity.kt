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
import com.google.android.material.navigation.NavigationView

lateinit var drawerLayout: DrawerLayout
lateinit var coordinatorLayout: CoordinatorLayout
lateinit var toolbar  : Toolbar
lateinit var frameLayout: FrameLayout
lateinit var navigationView: NavigationView

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
        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, R.string.open,R.string.close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.dashboard -> {
                    Toast.makeText(this@MainActivity , "Dashboard", Toast.LENGTH_SHORT).show()
                }

                R.id.favourites -> {
                    Toast.makeText(this@MainActivity , "Favourites", Toast.LENGTH_SHORT).show()
                }

                R.id.profile -> {
                    Toast.makeText(this@MainActivity , "Profile", Toast.LENGTH_SHORT).show()
                }

                R.id.about -> {
                    Toast.makeText(this@MainActivity , "About", Toast.LENGTH_SHORT).show()
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
}