package com.example.potapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Show_Symptoms : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_symptoms)
        val db = FirebaseFirestore.getInstance()

        val day1Query =
            FirebaseAuth.getInstance().currentUser?.email?.let {
                db.collection("Symptoms").document(
                    it
                ).collection("day1").get()
            }
        val day2Query =
            FirebaseAuth.getInstance().currentUser?.email?.let {
                db.collection("Symptoms").document(
                    it
                ).collection("day2").get()
            }
        val day3Query =
            FirebaseAuth.getInstance().currentUser?.email?.let {
                db.collection("Symptoms").document(
                    it
                ).collection("day3").get()
            }
        val day1Symptoms = mutableListOf<Sympo>()
        if (day1Query != null) {
            day1Query.addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val symptom = document.toObject(Sympo::class.java)
                    if (symptom != null) {
                        day1Symptoms.add(symptom)

                        // Find the TextView in your XML layout
                        val symptomTextView = findViewById<TextView>(R.id.sympttview)


                        symptomTextView.text = "Day 1:\n"
                        for ((i, textSymptom) in symptom.textSymptoms.withIndex()) {
                                symptomTextView.append("$i. $textSymptom\n")
                            }
                        symptomTextView.addTextChangedListener(object : TextWatcher {
                            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                                // Do nothing
                            }

                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                                if (!s.isNullOrEmpty()) {
                                    symptomTextView.setBackgroundResource(R.drawable.border) // Set the border
                                } else {
                                    symptomTextView.background = null // Remove the border
                                }
                            }

                            override fun afterTextChanged(s: Editable?) {
                                // Do nothing
                            }
                        })
                        symptomTextView.maxLines = 10
                        symptomTextView.isVerticalScrollBarEnabled = true



                    }
                }
            }
        }
        val day2Symptoms = mutableListOf<Sympo>()
        if (day2Query != null) {
            day2Query.addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val symptom = document.toObject(Sympo::class.java)
                    if (symptom != null) {
                        day2Symptoms.add(symptom)

                        // Find the TextView in your XML layout
                        val symptomTextView = findViewById<TextView>(R.id.symptt2view)


                        symptomTextView.text = "Day 2:\n"

                        for ((i, textSymptom) in symptom.textSymptoms.withIndex()) {
                            symptomTextView.append("$i. $textSymptom\n")
                        }
                        symptomTextView.addTextChangedListener(object : TextWatcher {
                            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                                if (!s.isNullOrEmpty()) {
                                    symptomTextView.setBackgroundResource(R.drawable.border) // Set the border
                                } else {
                                    symptomTextView.background = null // Remove the border
                                }
                                // Do nothing
                            }

                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                                if (!s.isNullOrEmpty()) {
                                    symptomTextView.setBackgroundResource(R.drawable.border) // Set the border
                                } else {
                                    symptomTextView.background = null // Remove the border
                                }
                            }

                            override fun afterTextChanged(s: Editable?) {
                                // Do nothing
                                if (!s.isNullOrEmpty()) {
                                    symptomTextView.setBackgroundResource(R.drawable.border) // Set the border
                                } else {
                                    symptomTextView.background = null // Remove the border
                                }
                            }
                        })

                    }
                }
            }
        }
        val day3Symptoms = mutableListOf<Sympo>()
        if (day3Query!= null) {
            day3Query.addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val symptom = document.toObject(Sympo::class.java)
                    if (symptom != null) {
                        day3Symptoms.add(symptom)

                        // Find the TextView in your XML layout
                        val symptomTextView = findViewById<TextView>(R.id.symptt3view)


                        symptomTextView.text = "Day 3:\n"
                        for ((i, textSymptom) in symptom.textSymptoms.withIndex()) {
                            symptomTextView.append("$i. $textSymptom\n")
                        }
                        symptomTextView.addTextChangedListener(object : TextWatcher {
                            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                                if (!s.isNullOrEmpty()) {
                                    symptomTextView.setBackgroundResource(R.drawable.border) // Set the border
                                } else {
                                    symptomTextView.background = null // Remove the border
                                }
                                // Do nothing
                            }

                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                                if (!s.isNullOrEmpty()) {
                                    symptomTextView.setBackgroundResource(R.drawable.border) // Set the border
                                } else {
                                    symptomTextView.background = null // Remove the border
                                }
                            }

                            override fun afterTextChanged(s: Editable?) {
                                if (!s.isNullOrEmpty()) {
                                    symptomTextView.setBackgroundResource(R.drawable.border) // Set the border
                                } else {
                                    symptomTextView.background = null // Remove the border
                                }
                                // Do nothing
                            }
                        })


                    }
                }
            }
        }
    }
}