package com.example.potapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class CurrentActivity : AppCompatActivity() {
    private lateinit var newList : List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_nav)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFB6C1")))
        supportActionBar?.apply {
            title= "Shark week"
        }





        val sharedPreferences = getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE)
        val selectedDate = sharedPreferences.getString("MY_VALUE",null)
        val days = selectedDate?.substring(8,10);
        val month = selectedDate?.substring(5,7);
        val intDays = days?.toInt()
        val intMonth = month?.toInt()
        println("abhi aayenge  numbers")
        val calender = Calendar.getInstance(TimeZone.getTimeZone("Asia/kolkata"))
        val currDate = calender.time
        val daysinCurr = calender.get(Calendar.DAY_OF_MONTH)
        val monthinCurr = calender.get(Calendar.MONTH)
        val db = FirebaseFirestore.getInstance();
        val clickedDaysList = mutableListOf<String>()
        val daysbtwn = mutableListOf<Int>()



        if (intMonth != null) {
            if(intMonth % 2 == 0){
                var count =1;
                val sum = intDays?.plus(28);
                var rem = (intDays?.plus(28))?.rem(30);
                if (rem != null ) {
                    if (sum != null) {
                        if(sum>30){
                            while ((rem % 30 > 31) && rem>3){

                                rem%=30;
                                count+=1;
                                println("Mai loop me kyu aaya?")
                                println(rem);
                            }

                            val totalmonth = intMonth + count;
                            println(intMonth)
                            println(count)

                            val displayText = findViewById<TextView>(R.id.tt2)
                            displayText.setText("You'll have next periods on \n Date: $rem-$totalmonth-2023 ");

                        }
                    }
                }
                val totalmonth = intMonth ;
                println("out of loop$intMonth")
                println(rem);
                println(count)

                println(daysinCurr)
                println(monthinCurr)
                val displayText = findViewById<TextView>(R.id.tt2)
//                if(monthinCurr+1==totalmonth){
//                    val daysin = rem?.minus(daysinCurr)
//                    displayText.setText("You'll have next periods in Days: $daysin ");
//                }
//                else{
//                    val i = 30-daysinCurr
//                    val daysin = i + rem!!
//                    displayText.setText("You'll have next periods in Days: $daysin ");
//
//                }
                displayText.setText("You'll have next periods on \n Date : $rem-$totalmonth-2023")


            }
            else{
                var count = 1;
                val sum = intDays?.plus(28);
                var rem = (intDays?.plus(28))?.rem(31);
                if (rem != null  ) {
                    if (sum != null) {
                        if(sum>31){
                            while ((rem % 30 > 31) && rem>3){
                                rem%=31;
                                count+=1;
                                println(intDays)
                                println(rem);
                                println(count);

                            }
                            val totalmonth = intMonth + count ;
                            println(intMonth)
                            println(totalmonth)
                            println("if sum ke andar executuion")
                            val displayText = findViewById<TextView>(R.id.tt2)
                            displayText.setText("You'll have next periods on \n Date: $rem-$totalmonth-2023 ");

                        }
                    }
                    val totalmonth = intMonth;
                    println(intMonth)
                    println(count)
                    val displayText = findViewById<TextView>(R.id.tt2)
                    println("if sum ke bahar execution")
                    displayText.setText("You'll have next periods on \n Date: $rem-$totalmonth-2023 ");
            }
        }
        }
        val btnDatePicker = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        // when floationg acition button is clicked
        btnDatePicker.setOnClickListener {

            // Initiation date picker with
            // MaterialDatePicker.Builder.datePicker()
            // and building it using build()
           val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")

            // Setting up the event for when ok is clicked
            datePicker.addOnPositiveButtonClickListener {
                Toast.makeText(this, "${datePicker.headerText} is selected", Toast.LENGTH_LONG).show()
                println("Calenderrrrrrrrrr ${datePicker.headerText}...")
                clickedDaysList.add(datePicker.headerText)
                val startInstant: Instant = Instant.ofEpochMilli(it.first)
                val endInstant: Instant = Instant.ofEpochMilli(it.second)

                val startDate: LocalDate =
                    LocalDateTime.ofInstant(startInstant, ZoneId.systemDefault()).toLocalDate()
                val endDate: LocalDate =
                    LocalDateTime.ofInstant(endInstant, ZoneId.systemDefault()).toLocalDate()
                val days = ChronoUnit.DAYS.between(startDate, endDate).toInt()
                daysbtwn.add(days)
                println("Startttttttttttt and Endddddddd $startDate - $endDate - $days ")
                newList = clickedDaysList


                val data = hashMapOf(
                    "dates" to newList,
                    "daysbtwn" to daysbtwn
                )
                FirebaseAuth.getInstance().currentUser?.email?.let { it1 ->
                    db.collection("Period_Tracker").document(
                        it1
                    ).set(data)
                }




            }

            // Setting up the event for when cancelled is clicked
            datePicker.addOnNegativeButtonClickListener {
                Toast.makeText(this, "${datePicker.headerText} is cancelled", Toast.LENGTH_LONG).show()
            }

            // Setting up the event for when back button is pressed
            datePicker.addOnCancelListener {
                Toast.makeText(this, "Date Picker Cancelled", Toast.LENGTH_LONG).show()
            }
        }



        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_tracker-> {
                    // Handle home menu item click
                    val intent = Intent(this , Period_Tracker::class.java)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_insights -> {
                    // Handle notifications menu item click
                    val intent = Intent(this , Cycle_Tracker::class.java)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_calculator -> {
                    // Handle notifications menu item click
                    val intent = Intent(this , QuestionActivity::class.java)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_log -> {
                    // Handle notifications menu item click
                    val intent = Intent(this , Show_Symptoms::class.java)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_nutrition -> {
                    // Handle notifications menu item click
                    val intent = Intent(this , nutritionActivity::class.java)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }
                else -> false
            }

        }

    }
}





