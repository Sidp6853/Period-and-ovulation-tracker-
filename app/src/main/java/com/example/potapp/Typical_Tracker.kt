package com.example.potapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

class Typical_Tracker : AppCompatActivity() {

    var avgPeriodLengthInDays by Delegates.notNull<Int>()
    var avgCycleLengthInDays by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_typical_tracker)
//        val calendarView = findViewById<CalendarView>(R.id.calendarView2)
//        var selectedDate: Date? = null
//        var firstClickDate: Date? = null
//        var secondClickDate: Date? = null
//        var numClicks = 0
//
//
//        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
//            // Convert the selected date to a Date object
//            val calendar = Calendar.getInstance()
//            calendar.set(year, month, dayOfMonth)
//            selectedDate = calendar.time
//
//
//
//            // Perform actions based on the number of clicks
//            numClicks++
//            when (numClicks) {
//                1 -> {
//                    // First click
//                    firstClickDate = selectedDate
//                    // Change the color of the selected date to red
//                    calendarView.dateTextAppearance = Color.RED
//                    // Store the date in Firestore
//                    // ...
//                }
//                2 -> {
//                    // Second click
//                    secondClickDate = selectedDate
//                    // Change the color of the selected date to red
//                    calendarView.dateTextAppearance = Color.RED
//                    // Calculate the difference between the two dates
//                    val diffInMillis = secondClickDate!!.time - firstClickDate!!.time
//                     avgPeriodLengthInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis).toInt()
//                    // Store the average period length in Firestore
//                    // ...
//
//                }
//                3 -> {
//                    // Third click
//                    // Change the color of the selected date to blue
//                    calendarView.dateTextAppearance = Color.BLUE
//                    // Calculate the difference between the first and third dates
//                    val diffInMillis = selectedDate!!.time - firstClickDate!!.time
//                    avgCycleLengthInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis).toInt()
//                    // Store the average cycle length in Firestore
//                    // ...
//                    val data = hashMapOf(
//                        "Third_date" to selectedDate,
//                        "First_date" to firstClickDate,
//                        "avgCycleLength" to avgCycleLengthInDays,
//                        "avgPeriodLength" to avgPeriodLengthInDays
//                    )
//                    val firestore = FirebaseFirestore.getInstance()
//                    FirebaseAuth.getInstance().currentUser?.email?.let {
//                        firestore.collection("myCollection")
//                            .document(it)
//                            .set(data)
//                            .addOnSuccessListener {
//                                // Success!
//                            }
//                            .addOnFailureListener { e ->
//                                // Error!
//                            }
//                    }
//
//                }
//            }
//        }
//        numClicks = 0;

    }
}