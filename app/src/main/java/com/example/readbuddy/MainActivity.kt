package com.example.readbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.readbuddy.R
import com.example.readbuddy.fragments.HomeFragment
import com.example.readbuddy.fragments.LeaderboardFragment
import com.example.readbuddy.fragments.ReadingListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val leaderboardFragment = LeaderboardFragment()
    private val readingListFragment = ReadingListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Set up the bottom navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        replaceFragment(homeFragment)
        // Deprecated, look into a different way to do this
        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> replaceFragment(homeFragment)
                R.id.menu_leaderboard -> replaceFragment(leaderboardFragment)
                R.id.menu_reading_list -> replaceFragment(readingListFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        //empty so the user can back button after sign on
    }
}