package com.example.notificationsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notificationsexample.databinding.ActivityYesBinding

class YesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityYesBinding.inflate(layoutInflater).also { binding = it }.root)

        intent.extras?.getString(YES_EXTRA_KEY)?.let {
            binding.tvText.text = it
        }
    }
}