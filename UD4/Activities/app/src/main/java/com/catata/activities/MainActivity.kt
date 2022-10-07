package com.catata.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.catata.activities.databinding.ActivityMainBinding

const val TAG = "LIFECYCLE"

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
        
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView(ActivityMainBinding.inflate(layoutInflater).also{
                    binding = it
                }.root)

        Log.d(TAG, "onCreate")

        binding.btnGoSecond.setOnClickListener {
            val i:Intent = Intent(this,SecondActivity::class.java)
            i.putExtra("GREETING", "Hi there")
            startActivity(i)
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.d(TAG, "onStop")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d(TAG, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "onDestroy")
    }

}