package com.example.internshiptest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.internshiptest.R
import com.example.internshiptest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, NewsFragment::class.java, null)
                .commit()
        }

    }
}