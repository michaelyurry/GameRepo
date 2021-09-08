package com.yurry.gamerepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yurry.gamerepo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_main)
    }
}