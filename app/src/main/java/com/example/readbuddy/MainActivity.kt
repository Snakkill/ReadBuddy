package com.example.readbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.readbuddy.R
import com.example.readbuddy.fragments.HomeFragment
import com.example.readbuddy.fragments.LeaderboardAdapter
import com.example.readbuddy.fragments.LeaderboardFragment
import com.example.readbuddy.fragments.ReadingListFragment
import com.example.readbuddy.model.User
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.random.Random

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

        // Deprecated, look into a different way to do this when time permits.
        // Still functions
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
        // Function responsible for swapping fragments
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        //empty so the user can back button after sign on
    }

    private fun uploadInfo() {
        // Create data
    }
}