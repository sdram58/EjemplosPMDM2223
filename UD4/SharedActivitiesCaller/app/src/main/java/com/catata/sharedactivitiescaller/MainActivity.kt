package com.catata.sharedactivitiescaller

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.catata.sharedactivitiescaller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var myResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data

            //When data go back we put it in the text view
            binding.tvGreeting.text = "${data?.getStringExtra(Intent.EXTRA_RETURN_RESULT)}"
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        binding.btnGoActivity2.setOnClickListener {
            sendShared()
        }
    }

    private fun sendShared() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Hi there! pass that to uppercase and send back.")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(//Selector that allow us choose destination app according the action and intent-filter
            sendIntent,
            "Hey choose an app to uppercase") //Title of selector window
        myResultLauncher.launch(shareIntent)
    }
}