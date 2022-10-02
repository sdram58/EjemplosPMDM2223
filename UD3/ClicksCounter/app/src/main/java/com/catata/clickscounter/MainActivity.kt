package com.catata.clickscounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catata.clickscounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var counter = 0  //creates a initial counter variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        binding.increaseCounter.setOnClickListener {
            binding.clicksCounter.text = "You have clicked ${++counter} times"
        }
    }
}