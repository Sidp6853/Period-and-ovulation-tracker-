package com.example.potapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        supportActionBar?.apply {
            title= "Period Calculator"
        }


        val db = FirebaseFirestore.getInstance();
        val myCollection = db.collection("Input");
        var auth: FirebaseAuth = Firebase.auth

        val periodLast = findViewById<EditText>(R.id.editTextTextPersonName);
        val avgLength = findViewById<EditText>(R.id.editTextTextPersonName3);
        periodLast.setBackgroundResource(R.drawable.edittext_bg)
        avgLength.setBackgroundResource(R.drawable.edittext_bg)
//        if(periodLast.text.isEmpty() || avgLength.text.isEmpty()){
//            Toast.makeText(this , "Please fill all fields", Toast.LENGTH_SHORT)
//                .show()
//            return
//
//
//        }


//         int periodLastString= periodLast.text.toString();
//        val avgLengthString = avgLength.text.toString();
//        val email = auth.currentUser?.email;
//        val uid = auth.currentUser?.uid;
//        if (periodLastString.isNotEmpty()) {
//            // Do something with the periodLastString value
//            println("Empty nahi hai guyzzz")
//        } else {
//            // Handle the error here (e.g. display a message to the user)
//            println("Empty hai guyzz")
//        }


//        val data = hashMapOf(
//            "Period last" to periodLastString,
//            "AvgLength" to avgLengthString,
//            "uid" to uid,
//            "email" to email,
//        )
        val predictButton : Button = findViewById(R.id.button);
        predictButton.setOnClickListener {
//

            var periodLastString = periodLast.text.toString()
            var avgLengthString = avgLength.text.toString()
//            try {
                var periodLastNum = periodLastString.toInt()
                var avgLengthNum = avgLengthString.toInt()
                println(periodLastNum)
                println(avgLengthNum)
//            } catch (e: NumberFormatException) {
//                // Handle the case where the user input is not a valid integer
//                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
//
//            }


            val sharedPreferences = getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE)
            val selectedDate = sharedPreferences.getString("MY_VALUE",null)
            val days = selectedDate?.substring(8,10);
            val month = selectedDate?.substring(5,7);
            val intDays = days?.toInt()
            val intMonth = month?.toInt()
            println("abhi aayenge  numbers")


            if (intMonth != null) {
                if(intMonth % 2 == 0){
                    var count =1;
                    val sum = intDays?.plus(avgLengthNum);
                  var rem = (intDays?.plus(avgLengthNum))?.rem(30);
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

                                val displayText = findViewById<TextView>(R.id.textView)
                                displayText.setText("You'll have next periods on \n Date: $rem-$totalmonth-2023 ");
                                return@setOnClickListener
                            }
                        }
                    }
                    val totalmonth = intMonth ;
                    println(intMonth)
                    println(count)
                    val displayText = findViewById<TextView>(R.id.textView)
                    displayText.setText("You'll have next periods on \n Date: $rem-$totalmonth-2023 ");
                    return@setOnClickListener
                }
                else{
                    var count = 1;
                    val sum = intDays?.plus(avgLengthNum);
                    var rem = (intDays?.plus(avgLengthNum))?.rem(31);
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
                                val displayText = findViewById<TextView>(R.id.textView)
                                displayText.setText("You'll have next periods on \n Date: $rem-$totalmonth-2023 ");
                                return@setOnClickListener
                            }
                        }
                        val totalmonth = intMonth;
                        println(intMonth)
                        println(count)
                        val displayText = findViewById<TextView>(R.id.textView)
                        println("if sum ke bahar execution")
                        displayText.setText("You'll have next periods on \n Date: $rem-$totalmonth-2023 ");
                        return@setOnClickListener
                    }

                }
            }

        }

//        val nxtButton = findViewById<ImageButton>(R.id.nxtimageButton)
//        nxtButton.setOnClickListener() {
//            val intent = Intent(this, Cycle_Tracker::class.java)
//            startActivity(intent)
//        }


    }
}






