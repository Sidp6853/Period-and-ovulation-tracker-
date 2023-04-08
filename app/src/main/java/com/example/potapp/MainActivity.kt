package com.example.potapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.Toast
//import com.example.potapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
//    public var dayinput = seletedDate.subString(0,4);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lateinit var auth: FirebaseAuth
        auth = Firebase.auth
        var calendarView = findViewById<CalendarView>(R.id.calendarView)
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
           val selectedDate = formatDate(year, month, dayOfMonth)
            val sharedPreferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("MY_VALUE",selectedDate)
            editor.apply()

            var imageView = findViewById<ImageView>(R.id.imageView2)
            imageView.setImageResource(R.drawable.my_image1)


            val border = GradientDrawable()
            border.setColor(Color.WHITE)
            border.setStroke(2, Color.GRAY)

            calendarView.background = border

            val email = auth.currentUser?.email;
            val uid = auth.currentUser?.uid;
            // Create a new document in the "dates" collection with the selected date
            val db = FirebaseFirestore.getInstance();
            val date = hashMapOf(
                "date" to selectedDate,
                "uid" to uid,
                "email" to email,

            )
            db.collection("dates")
                .add(date)
                .addOnSuccessListener { documentReference ->
                    // Handle success here
                    Toast.makeText(this , "Doc added", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this , CurrentActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener { e ->
                    // Handle errors here
                    Toast.makeText(this , "Doc not added error $e", Toast.LENGTH_SHORT)
                        .show()
                }
        }
    }

    private fun formatDate(year: Int, month: Int, dayOfMonth: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(calendar.time)
    }

    }

