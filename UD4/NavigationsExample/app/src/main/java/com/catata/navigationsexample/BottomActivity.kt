package com.catata.navigationsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.catata.navigationsexample.databinding.ActivityBottomBinding

class BottomActivity : AppCompatActivity() {
    lateinit var binding: ActivityBottomBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityBottomBinding.inflate(layoutInflater).also { binding = it }.root)

        setSupportActionBar(binding.toolbar);

        appBarConfiguration = AppBarConfiguration(setOf(
            //Top-level destinations
            R.id.bottom1Fragment,
            R.id.bottom2Fragment,

            )
        )

        val navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?)!!.navController

        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);
    }
}