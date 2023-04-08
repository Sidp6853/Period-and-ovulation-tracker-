package com.example.potapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.apply {
            title= "Shark week"
        }


        auth = Firebase.auth

        val registerText: TextView = findViewById(R.id.textView_no_acc_register)
         registerText.setOnClickListener {
             val intent = Intent(this , RegisterActivity::class.java)
             startActivity(intent)
         }
        val loginButton = findViewById<Button>(R.id.button_login)

        loginButton.setOnClickListener {
            performLogin()
        }

    }


    private fun performLogin(){
        val email= findViewById<EditText>(R.id.textView_email_login)
        val psswd = findViewById<EditText>(R.id.textView_password_login)

        //null checks on input
        if(email.text.isEmpty()|| psswd.text.isEmpty()){
            Toast.makeText(this , "Please fill all details",Toast.LENGTH_SHORT)
                .show()
            return
        }
        val emailInput = email.text.toString()
        val psswdInput = psswd.text.toString()


        auth.signInWithEmailAndPassword(emailInput, psswdInput)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, move to main Activity
                    val intent = Intent(this , MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "lOGGEd in Successfully.",
                        Toast.LENGTH_SHORT).show()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
            .addOnFailureListener {
                Toast.makeText(baseContext , "Authentication failed ${it.localizedMessage}",Toast.LENGTH_SHORT).show()

            }
    }
}

