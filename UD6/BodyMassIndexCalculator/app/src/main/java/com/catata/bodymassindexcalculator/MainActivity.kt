package com.catata.bodymassindexcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catata.bodymassindexcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)


    }



}