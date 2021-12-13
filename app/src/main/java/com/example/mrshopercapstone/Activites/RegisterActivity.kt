package com.example.mrshopercapstone.Activites

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
import com.example.mrshopercapstone.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        ///////////////////////////////////////////////////////////
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
        val firstName: EditText = findViewById(R.id.firstnamere_edittext)
        val lastName: EditText = findViewById(R.id.lastnamere_editText)
        val conformPassowrd: EditText = findViewById(R.id.ConforimPasswordre_editText)
        val emailAddress: EditText = findViewById(R.id.Emailre_editText)
        val password: EditText = findViewById(R.id.passwordre_editText)
        val registerButton: Button = findViewById(R.id.register_button)
        val loginTextView: TextView = findViewById(R.id.loginre_textview)

        loginTextView.setOnClickListener(){
            startActivity(Intent(this,loginTextView::class.java))
            finish()
        }
        registerButton.setOnClickListener(){
            val firstname: String = firstName.text.toString()
            val lastname: String = lastName.text.toString()
            val conformpassword: String = conformPassowrd.text.toString()
            val email: String = emailAddress.text.toString()
            val password: String = password.text.toString()

            // this is condition for the firstname and lastname and conformpassword and email and password
            if (firstname.isNotBlank() && lastname.isNotBlank() && conformpassword.isNotBlank() && email.isNotBlank() && password.isNotBlank()){
//                if password == conformpassword) {
//                    if (validator.emailIsValid(email)) {
//                        if (validator.passwordIsValid(passwor))
            // to get the user info in the firebase
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(){
                            task ->
                        if (task.isSuccessful){
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(this,"User Registered Successful" , Toast.LENGTH_SHORT).show()
                            // Navigate to main activity
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("UserId", firebaseUser.uid)
                            intent.putExtra("Email", firebaseUser.email)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(this,task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

            }
        }
    }
}
