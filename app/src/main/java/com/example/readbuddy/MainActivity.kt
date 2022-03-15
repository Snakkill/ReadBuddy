package com.example.readbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.readbuddy.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        //empty so the user can back button after sign on
    }
}