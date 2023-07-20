package com.example.l1cert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    //declare floating action buttons late init
    private lateinit var firebaseAuth: FirebaseAuth

    lateinit var btnGoogle: FloatingActionButton
    lateinit var btnFacebook: FloatingActionButton
    lateinit var btnPhone: FloatingActionButton
    lateinit var btnSignIn : Button
    lateinit var btnSignUp : TextView
    lateinit var btnForgotPassword : TextView
    lateinit var emailText : EditText
    lateinit var passwordText : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // declare button and text variables
        btnGoogle = findViewById(R.id.googleBtn)
        btnFacebook = findViewById(R.id.facebookBtn)
        btnPhone = findViewById(R.id.floatingActionButton5)
        btnSignIn = findViewById(R.id.signInBtn)
        btnSignUp = findViewById(R.id.signUpText)
        btnForgotPassword = findViewById(R.id.textView3)
        emailText = findViewById(R.id.inputEmail)
        passwordText = findViewById(R.id.inputPassword)

        firebaseAuth = Firebase.auth



        //Click listeners
        btnGoogle.setOnClickListener {
            Toast.makeText(this, "Google button clicked", Toast.LENGTH_SHORT).show()

        }
        btnFacebook.setOnClickListener {
            Toast.makeText(this, "Facebook button clicked", Toast.LENGTH_SHORT).show()
        }
        btnPhone.setOnClickListener {
//            Toast.makeText(this, "Phone  button clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent( this, PhoneActivity::class.java)
            startActivity(intent)
        }
        btnSignIn.setOnClickListener {
//            Toast.makeText(this, "SignIn  button clicked", Toast.LENGTH_SHORT).show()
            if (emailText.text.toString().length != 0 && passwordText.text.toString().length != 0){
                signIn(emailText.text.toString(), passwordText.text.toString())
            } else{
                Toast.makeText(this, "Fill the required fields", Toast.LENGTH_SHORT).show()

            }


        }
        btnSignUp.setOnClickListener {
            Toast.makeText(this, "Signup  text clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent( this, SignupActivity::class.java)
            startActivity(intent)
        }
        btnForgotPassword.setOnClickListener {
//            Toast.makeText(this, "Forgot password text clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent( this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }




    }

    private fun signIn (email:String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
            if (task.isSuccessful){
                val user = firebaseAuth.currentUser
                Toast.makeText(baseContext, "Succesful login", Toast.LENGTH_SHORT).show()
                // intent a la activity login
                val intent = Intent (this, LoggedInActivity::class.java)
                    startActivity(intent)
            } else {
                Toast.makeText(baseContext, "Error, check credentials", Toast.LENGTH_SHORT).show()

            }
        }

    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null){
            startActivity(Intent(this , LoggedInActivity::class.java))
        }
    }


}