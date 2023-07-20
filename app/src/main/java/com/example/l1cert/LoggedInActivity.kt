package com.example.l1cert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoggedInActivity : AppCompatActivity() {

    lateinit var signOutBtn : Button
    private lateinit var  firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)

        signOutBtn = findViewById(R.id.signOutBtn)
        firebaseAuth= Firebase.auth

        signOutBtn.setOnClickListener {
            signOut()
        }


    }

    override fun onBackPressed() {
        return
    }

    private fun signOut (){
        firebaseAuth.signOut()
        Toast.makeText(baseContext, "Signed out successfully", Toast.LENGTH_SHORT).show()
        val intent = Intent( this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}