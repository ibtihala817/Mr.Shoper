package com.example.mrshopercapstone.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mrshopercapstone.R
import com.example.mrshopercapstone.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


         val navHostFragment =
        supportFragmentManager.findFragmentById(R.id.fragmentContainerView6) as NavHostFragment
        navController = navHostFragment.navController

    setupActionBarWithNavController(navController)
     NavigationUI.setupWithNavController(binding.bottomNavigationView,navController )
    }
//     if you want to back to the last fragment from where you come
//    here just user the navigateUp methods of NavController
      override fun onSupportNavigateUp(): Boolean {
          return navController.navigateUp()
      }

}