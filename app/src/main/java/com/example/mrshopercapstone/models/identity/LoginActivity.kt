package com.example.mrshopercapstone.models.identity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mrshopercapstone.view.main.MainActivity
import com.example.mrshopercapstone.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ////////////////////////////////////////////////////////////
        // for the top bar to gone in the login and set the fullscreen in android R
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
        // declaring the variables
        val emailAddress: EditText = findViewById(R.id.emailId_editText)
        val password : EditText = findViewById(R.id.emailId_editText)
        val loginButton: Button = findViewById(R.id.register_button)
        val registerTextView: TextView = findViewById(R.id.register_textView)
//        // display the register textview
        registerTextView.setOnClickListener(){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        // display the loginButton
        loginButton.setOnClickListener(){
            val email: String = emailAddress.text.toString()
            val password: String = password.text.toString()
        // setting condition if the email is valid then let the user to save the email and the password in the firebase
            if(email.isNotEmpty()&& password.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(){
                            task ->
                        if (task.isSuccessful){
                            Toast.makeText(this,"User Logged in Successfully", Toast.LENGTH_SHORT)
                                .show()
                            //Navigate to MainActivity
                            val intent = Intent(this, RegisterActivity::class.java)
                            intent.putExtra("UserId", FirebaseAuth.getInstance().currentUser!!.uid)
                            intent.putExtra("Email", FirebaseAuth.getInstance().currentUser!!.email)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(this,task.exception!!.message.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }
        }
    }
}
