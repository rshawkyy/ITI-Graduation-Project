package com.example.finalproject.Screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Rect
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.finalproject.MainActivity
import com.example.finalproject.R
import com.example.finalproject.SharedPreference.SharedPreferenceManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity2 : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var navView: NavigationView
    private lateinit var drawer: DrawerLayout
    private lateinit var sharedPreferences: SharedPreferences
    private  val USER_NAME ="name"
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navController: NavController

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2) // Make sure this is the correct layout
        sharedPreferences = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString(USER_NAME, "default_value")
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
        val headerView = navView.getHeaderView(0)
        val nameTextView = headerView.findViewById<TextView>(R.id.nav_header_name)
        nameTextView.text =name
        adjustForKeyboard()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        bottomNavView = findViewById(R.id.bottom_navigation)
        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragmentbottom -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.favoriteFragmentbottom -> {
                    navController.navigate(R.id.favoritesFragment)
                    true
                }

                R.id.searchFragmentbottom -> {
                    navController.navigate(R.id.searchFragment)
                    true
                }

                else -> false
            }
        }

        drawer = findViewById(R.id.drawer)
        val toggle = ActionBarDrawerToggle(
            this, drawer, R.string.nav_drawer_start,
            R.string.nav_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> {
                navController.navigate(R.id.homeFragment)
                bottomNavView.selectedItemId = R.id.homeFragmentbottom
                drawer.closeDrawers()
                return true
            }

            R.id.action_fav -> {
                navController.navigate(R.id.favoritesFragment)
                bottomNavView.selectedItemId = R.id.favoriteFragmentbottom
                drawer.closeDrawers()
                return true
            }

            R.id.action_search -> {
                navController.navigate(R.id.searchFragment)
                bottomNavView.selectedItemId = R.id.searchFragmentbottom

                drawer.closeDrawers()
                return true
            }

            R.id.action_SignOut -> {
                SharedPreferenceManager.setLoggedIn(this, false)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                drawer.closeDrawers()
                return true
            }
            R.id.action_about -> {
                navController.navigate(R.id.aboutFragment)

                drawer.closeDrawers()
                return true
            }
        }
        drawer.closeDrawers()
        return false
    }

    private fun adjustForKeyboard() {
        val rootView = window.decorView.findViewById<View>(android.R.id.content)

        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.height
            val keypadHeight = screenHeight - rect.bottom

            if (keypadHeight > screenHeight * 0.15) {
                // Keyboard is visible
                bottomNavView.translationY = keypadHeight.toFloat()
            } else {
                // Keyboard is hidden
                bottomNavView.translationY = 0f
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_sign_out -> {
                SharedPreferenceManager.setLoggedIn(this, false)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                drawer.closeDrawers()
                true
            }
            R.id.menu_about_creator -> {
                navController.navigate(R.id.aboutFragment)

                drawer.closeDrawers()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
