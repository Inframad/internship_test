package com.example.internshiptest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.internshiptest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        (application as App).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}