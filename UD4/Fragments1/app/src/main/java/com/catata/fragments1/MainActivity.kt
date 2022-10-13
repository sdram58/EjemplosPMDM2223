package com.catata.fragments1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(), OnFragmentActionsListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClickFragmentButton(text:String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}

interface OnFragmentActionsListener {
    fun onClickFragmentButton(text:String)
}