package com.example.mrshopercapstone.view.main

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.mrshopercapstone.Repository.ApiRepositoryService
import com.example.mrshopercapstone.models.identity.LoginActivity
import com.example.mrshopercapstone.databinding.ActivitySplash2Binding
import com.example.mrshopercapstone.models.identity.RegisterActivity

const val SHARED_PREF_FILE="login state"
const val STATE="state"
const val USER_ID= "userId"
class Splash : AppCompatActivity() {

    private lateinit var binding: ActivitySplash2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        ////////////////////////////////////////////////////////////
        // for the status bar to gone in the splash and set the fullscreen in android R
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        //////////////////////////////////////////////////////////////
       // this is for hiding the Action bar
       supportActionBar?.hide()
       //////////////////////////////////////////////
        binding = ActivitySplash2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPref = getSharedPreferences(SHARED_PREF_FILE,Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)

        ApiRepositoryService.init(this)

        binding.layout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {

            }
            // shared preferences
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                if (sharedPref.getBoolean(STATE,false)) {
                    val intent = Intent(this@Splash, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    val intent = Intent(this@Splash, onBoard::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {

            }

        })
    }
}