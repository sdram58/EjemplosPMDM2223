package com.catata.splashscreenwithapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screenSplash.setKeepOnScreenCondition{
            //retrieving initial data
            Thread.sleep(2500)

            false
        }
    }
}