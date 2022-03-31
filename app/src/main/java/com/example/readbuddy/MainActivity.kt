package com.example.readbuddy

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.readbuddy.fragments.HomeFragment
import com.example.readbuddy.fragments.LeaderboardFragment
import com.example.readbuddy.fragments.ReadingListFragment
import com.example.readbuddy.model.User
import com.example.readbuddy.viewmodel.UserViewModel
import com.example.readbuddy.viewmodel.UserViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val leaderboardFragment = LeaderboardFragment()
    private val readingListFragment = ReadingListFragment()
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userViewModel: UserViewModel by viewModels {
            UserViewModelFactory((application as UserApplication).repository)
        }


        val userData : User? = intent?.getParcelableExtra<User>("user_data")
        if (userData != null) {
            userViewModel.addUser(userData)
        }
        //
            auth = Firebase.auth

        // Set up the bottom navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        replaceFragment(homeFragment)

        // Deprecated, look into a different way to do this when time permits.
        // Still functions
        bottomNav.selectedItemId = R.id.menu_home; // Set the selected item
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu items for use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_logout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_logout_btn -> {
                Toast.makeText(this, "Logout button pressed", Toast.LENGTH_SHORT).show()
                auth.signOut()
                val intent = Intent(this,GoogleSSO::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                //clear back stack
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}