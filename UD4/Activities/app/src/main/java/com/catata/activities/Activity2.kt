package com.catata.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catata.activities.MainActivity.Companion.EXTRA_ANSWER
import com.catata.activities.MainActivity.Companion.EXTRA_GREETING
import com.catata.activities.MainActivity.Companion.KEY_EXTRA_RESULT
import com.catata.activities.databinding.ActivitySecondBinding
import com.catata.activities.model.Person

class Activity2 : AppCompatActivity() {
    private lateinit var binding:ActivitySecondBinding
        
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView(ActivitySecondBinding.inflate(layoutInflater).also{
                    binding = it
                }.root)


        getData()

        binding.btnBack.setOnClickListener {
            val text = binding.etName.text.toString()


            val returnIntent = Intent().apply {
                putExtra(KEY_EXTRA_RESULT, text)
            }   //Creates a new Intent with editText content as an extra

            if(text != "")
                setResult(RESULT_OK, returnIntent) //The action went ok.
            else
                setResult(RESULT_CANCELED, returnIntent)

            finish() //Finish and close this activity



        }

    }

    private fun getData() {

        if(intent.hasExtra(MainActivity.KEY_EXTRA_RESULT_PARCELABLE)){
            val person = intent.getParcelableExtra<Person>(MainActivity.KEY_EXTRA_RESULT_PARCELABLE)

            binding.tvTop.text = "${person?.name ?: "No name"} ${person?.surname ?: "No surname"}"
        }

        /*if(intent.hasExtra(EXTRA_GREETING)){
            val greeting = intent.getStringExtra(EXTRA_GREETING)

            binding.tvTop.text = greeting ?: "No extra"
        }*/

    }
}