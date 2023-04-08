package com.example.potapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.potapp.Adapter.Horizontal_RecyclerView
import com.example.potapp.Adapter.Mood_Hori_RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase

class Cycle_Tracker : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var sympList: ArrayList<Symptoms>
    private lateinit var moodList : ArrayList<Mood>
    private lateinit var adapter: Horizontal_RecyclerView
    private lateinit var adapter2: Mood_Hori_RecyclerView
    private val clickedImageList = mutableListOf<Int>()
    private val clickedTextList = mutableListOf<String>()


    private fun addDataToList(){
        sympList.add(Symptoms(R.drawable.cramps , "Cramps"))
        sympList.add(Symptoms(R.drawable.breasts_tender , "Tender Breasts"))
        sympList.add(Symptoms(R.drawable.headache , "Headache"))
        sympList.add(Symptoms(R.drawable.cravings , "Cravings"))
        sympList.add(Symptoms(R.drawable.constipation , "Constipation"))
        sympList.add(Symptoms(R.drawable.diahhrea , "Diahhrea"))
        sympList.add(Symptoms(R.drawable.acne , "Acne"))

        moodList.add(Mood(R.drawable.calm , "Calm"))
        moodList.add(Mood(R.drawable.happy_mood , "Happy"))
        moodList.add(Mood(R.drawable.energetic , "Energetic"))
        moodList.add(Mood(R.drawable.frisky , "Frisky"))
        moodList.add(Mood(R.drawable.moo , "Mood Swings"))



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cycle_tracker)
        supportActionBar?.apply {
            title= "Shark week"
        }

        lateinit var auth: FirebaseAuth


        auth = Firebase.auth

        val editTextpg = findViewById<EditText>(R.id.editTextNumber)

        val db = FirebaseFirestore.getInstance();
        val collectionRef = db.collection("Symptoms")
        val emailId = FirebaseAuth.getInstance().currentUser?.email
        val docRef = emailId?.let { collectionRef.document(it) }


        val imageButton1 = findViewById<ImageButton>(R.id.imageButtonlight)
        val imageButton2 = findViewById<ImageButton>(R.id.imageButton2)
        val imageButton3 = findViewById<ImageButton>(R.id.imageButton3)


// Set OnClickListener for the first ImageButton
        imageButton1.setOnClickListener {
            // Reduce opacity of other two ImageButtons
            val flow_rate = "low";
            imageButton2.alpha = 0.5f
            imageButton3.alpha = 0.5f
        }

// Set OnClickListener for the second ImageButton
        imageButton2.setOnClickListener {
            // Reduce opacity of other two ImageButtons
            val flow_rate = "Medium"

            imageButton1.alpha = 0.5f
            imageButton3.alpha = 0.5f
        }

// Set OnClickListener for the third ImageButton
        imageButton3.setOnClickListener {
            // Reduce opacity of other two ImageButtons
         val flow_rate = "high"
            imageButton1.alpha = 0.5f
            imageButton2.alpha = 0.5f
        }
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView2 = findViewById(R.id.recyclerviewtwo)

        sympList = ArrayList()
        moodList = ArrayList()
        addDataToList()

        adapter = Horizontal_RecyclerView(sympList)
        adapter2 = Mood_Hori_RecyclerView(moodList)

        recyclerView.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false )
        recyclerView2.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false )
        recyclerView.adapter = adapter
        recyclerView2.adapter = adapter2

        adapter.setOnItemClickListner(object: OnItemClickListner{
            override fun onItemClick(view: View, position: Int) {
                var str = editTextpg.text.toString()
                val daycolref = docRef?.collection("day${str}")
                val docdaycol = daycolref?.document("$str-$emailId")
                val item = sympList[position]
                clickedTextList.add(item.sympText)
                clickedImageList.add(item.sympImage)
                val data = hashMapOf(
                    "imageResourceSymptoms" to clickedImageList,
                    "textSymptoms" to clickedTextList,
                    "mail" to emailId,
                    "day" to editTextpg.text.toString()
                )
                if (docRef != null) {
                    if (daycolref != null) {
                        if (docdaycol != null) {
                            docdaycol.set(data , SetOptions.merge()).addOnSuccessListener{
                                Log.d(TAG , "Doc added")
                            }.addOnFailureListener {
                                Log.w(TAG , "Error occoured")
                            }
                        }
                    }
                }

            }

        })



//            .addOnSuccessListener {
//                Log.d(TAG , "Doc added")
//            }
//            .addOnFailureListener {
//                Log.w(TAG, "Error occoured")
//            }



        }
    }
