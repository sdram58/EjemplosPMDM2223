package com.catata.sharedactivitiesreceiver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catata.sharedactivitiesreceiver.databinding.ActivitySharedBinding

class SharedActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySharedBinding

    var text:String? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    
        setContentView(ActivitySharedBinding.inflate(layoutInflater).also { binding = it }.root)


        getData()

        binding.btnSendBack.setOnClickListener {
            val intent = Intent().apply {
                putExtra(Intent.EXTRA_RETURN_RESULT, text?:"No text")
            }

            setResult(RESULT_OK,intent)
            finish()
        }
        
    }

    private fun getData() {
        val data = intent


        data?.let{
            if(data.hasExtra(Intent.EXTRA_TEXT))
                text = data.getStringExtra(Intent.EXTRA_TEXT).toString().uppercase()
            binding.tvShared.text = text
        }
    }
}