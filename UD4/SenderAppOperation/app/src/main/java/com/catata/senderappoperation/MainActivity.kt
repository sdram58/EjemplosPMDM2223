package com.catata.senderappoperation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catata.senderappoperation.databinding.ActivityMainBinding
import com.catata.senderappoperation.model.Model

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val model = Model(0.0,1.0, Model.Operation.SUM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView( ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)


        binding.btnSendOp.setOnClickListener{
            sendOp()
        }


    }

    private fun sendOp() {

    }
}