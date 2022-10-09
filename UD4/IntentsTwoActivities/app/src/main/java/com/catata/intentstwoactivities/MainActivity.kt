package com.catata.intentstwoactivities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.catata.intentstwoactivities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val userName:String = "Carlos"
    private val surName:String = "Tarazona"
    companion object{ //A companion objects acts like an static class in Java
        val KEY_EXTRA_NAME:String ="MY_KEY_EXTRA_NAME"
        val KEY_EXTRA_SURNAME:String ="MY_KEY_EXTRA_SURNAME"
        val KEY_EXTRA_RESULT:String ="MY_KEY_EXTRA_RESULTS"
    }

    var resultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                //Here we have the result of Activity2
                val data: Intent? = result.data
                //We use the extra included in the returned intent
                binding.tvGreeting.text =
                    "Hello ${data?.getStringExtra(KEY_EXTRA_RESULT) ?: "No return"}"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      
        setContentView( ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        binding.btnGoActivity2.setOnClickListener {
            openActivity2()
        }
    }


    private fun openActivity2() {
        val intent = Intent(this, Activity2::class.java).apply {
            putExtra(KEY_EXTRA_NAME, userName)
            putExtra(KEY_EXTRA_SURNAME, surName)
        }

        //startActivity(intent)
        resultLauncher.launch(intent)
    }
}