package com.example.mrshopercapstone.models.identity

import android.annotation.SuppressLint
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
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.example.mrshopercapstone.R
import com.example.mrshopercapstone.models.items.UserProfile
//import com.example.mrshopercapstone.view.ViewModel.ProfileUserViewModel
import com.example.mrshopercapstone.view.main.MainActivity
import com.example.mrshopercapstone.view.main.ProfileUserViewModel

import com.example.mrshopercapstone.view.main.STATE
import com.example.mrshopercapstone.view.main.USER_ID
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {
    val userinfo = UserProfile()
    private val profileUserViewModel: ProfileUserViewModel by viewModels()
    @SuppressLint("CommitPrefEdits")
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
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
        registerButton.setOnClickListener(){
            val firstname: String = firstName.text.toString()
            val lastname: String = lastName.text.toString()
            val conformpassword: String = conformPassowrd.text.toString()
            val email: String = emailAddress.text.toString()
            val passwordre: String = password.text.toString()

            // this is condition for the firstname and lastname and confirm password and email and password
            if (firstname.isNotBlank() && lastname.isNotBlank() && conformpassword.isNotBlank() && email.isNotBlank() && passwordre.isNotBlank()){

            // to get the user info in the firebase
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, passwordre)
                    .addOnCompleteListener(){
                            task ->
                        // if the  user success login show is user registered successful
                        if (task.isSuccessful){
                            //firebase registered user
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            ///// save the register data to the logout
                            userinfo.apply {
                                FirstName = firstname
                                LastName = lastname
                                Email = email
                                profileUserViewModel.save(userinfo)
                            }

                            Toast.makeText(this,"User Registered Successful" , Toast.LENGTH_SHORT)
                                .show()
                            /////////////////////////////
                            shareEditor = sharePref.edit()
                            shareEditor.putBoolean(STATE,true)
                            shareEditor.putString(USER_ID,FirebaseAuth.getInstance().currentUser!!.uid)
                            shareEditor.commit()
                           ///////////////////////////////
                            // Navigate to main activity
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("UserId", firebaseUser.uid)
                            intent.putExtra("Email", firebaseUser.email)
                            startActivity(intent)
                            finish()
                        }
                        //if the user not success register show this message
                        else{
                            Toast.makeText(this,task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

            }
        }
    }
}
