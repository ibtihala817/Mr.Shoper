package com.example.mrshopercapstone.view.main

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.example.mrshopercapstone.R
import com.example.mrshopercapstone.models.identity.LoginActivity
import com.example.mrshopercapstone.models.identity.RegisterActivity

class onBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board)
        ///Hide actionbar
        supportActionBar?.hide()
        ////// for the status bar to gone in the splash and set the fullscreen in android R
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        ///////////////////////////////////////////////////////////////////////////////////////////
        val shopButton: Button = findViewById(R.id.goShoppingButton)

        shopButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        /////////////////////////////////////////////////////////////////////////////////////////
        val registerOnBoardTextView: TextView = findViewById(R.id.registetOnBoardTextView)

        registerOnBoardTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        ////////////////////////////////////////////////////////////////////////////////////
    }
}