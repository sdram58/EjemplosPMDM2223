package com.catata.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.catata.activities.databinding.ActivityMainBinding
import com.catata.activities.model.Person

const val TAG = "LIFECYCLE"

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    companion object{
        val EXTRA_GREETING = "MY_KEY_EXTRA_GREETING"
        val EXTRA_ANSWER = "MY_KEY_EXTRA_ANSWER"
        val KEY_EXTRA_RESULT = "MY_KEY_EXTRA_RESULT"
        val KEY_EXTRA_RESULT_PARCELABLE = "MY_KEY_EXTRA_GREETING_PARCELABLE"
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            //Here we have the result of Activity2
            val data: Intent? = result.data
            //We use the extra included in the returned intent
            val name= data?.getStringExtra(KEY_EXTRA_RESULT)?:"No user"
            binding.tvGreeting.text = getString(R.string.hello_text,name)
        }
    }
        
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView(ActivityMainBinding.inflate(layoutInflater).also{
                    binding = it
                }.root)

        Log.d(TAG, "onCreate")

        getIntentData()

        binding.btnGoActivity2.setOnClickListener {

            val person = Person("Carlos", "Tarazona")

            val intent = Intent(this, Activity2::class.java).apply {
                putExtra(KEY_EXTRA_RESULT_PARCELABLE, person)
            }

            //i.putExtra("GREETING", "Hi there")

            startActivity(intent)

        }


    }

    private fun getIntentData() {
        if(intent.hasExtra(EXTRA_ANSWER))
        {
            binding.tvGreeting.text = intent.getStringExtra(EXTRA_ANSWER)
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