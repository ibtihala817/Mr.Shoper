package com.example.mrshopercapstone

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.mrshopercapstone.Activites.LoginActivity
import com.example.mrshopercapstone.databinding.ActivitySplash2Binding


class Splash : AppCompatActivity() {

    private lateinit var binding: ActivitySplash2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        ////////////////////////////////////////////////////////////
        // for the top bar to gone in the splash and set the fullscreen in android R
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
        super.onCreate(savedInstanceState)



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

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                val intent = Intent(this@Splash, LoginActivity::class.java)
                startActivity(intent)
                finish()
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