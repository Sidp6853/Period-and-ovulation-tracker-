package com.example.potapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.apply {
            title= "Shark week"
        }

       // Initialize firebase auth
        auth = Firebase.auth

        val loginTextView: TextView = findViewById(R.id.textView_have_acc_login_)
        loginTextView.setOnClickListener {
            val intent = Intent(this , LoginActivity::class.java)
            startActivity(intent)


        }
        val registerButton: Button = findViewById(R.id.button_register)
        registerButton.setOnClickListener {


            performSignUp()
        }
//      lets get email and password from user

    }

    private fun performSignUp() {
        val email = findViewById<EditText>(R.id.textView_email_register)
        val passwd = findViewById<EditText>(R.id.textView_password_register)

        if(email.text.isEmpty() || passwd.text.isEmpty()){
            Toast.makeText(this , "Please fill all fields", Toast.LENGTH_SHORT)
                .show()
            return


        }

        val inputEmail = email.text.toString()
        val inputPasswd = passwd.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail, inputPasswd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, let's move to the next actvity i.e MainActivity.kt
                    val intent = Intent(this , MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Signed Up Successfully.",
                        Toast.LENGTH_SHORT).show()
                    val user = hashMapOf(
                        "email" to email.text.toString(),


                    )
                    val db = Firebase.firestore;
                    db.collection("users")
                        .add(user)
                        .addOnSuccessListener { documentReference ->
                            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
            .addOnFailureListener {
                Toast.makeText(this , "Some error occoured ${it.localizedMessage}",Toast.LENGTH_SHORT).show()
            }
    }

}