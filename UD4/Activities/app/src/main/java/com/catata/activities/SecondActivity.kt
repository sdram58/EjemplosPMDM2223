package com.catata.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catata.activities.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySecondBinding
        
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView(ActivitySecondBinding.inflate(layoutInflater).also{
                    binding = it
                }.root)
        
        val greeting = intent.getStringExtra("GREETING")

        binding.textView2.text = greeting

    }
}