package com.example.potapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.model.mutation.Precondition.exists
import io.grpc.okhttp.internal.Platform.get
import java.lang.reflect.Array.get
import java.nio.file.Files.exists
import java.nio.file.Paths.get
import kotlin.coroutines.EmptyCoroutineContext.get

class Period_Tracker : AppCompatActivity() {
    //    lateinit var barDataSet:BarDataSet
//    lateinit var barlist:ArrayList<BarEntry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_period_tracker)
        supportActionBar?.apply {
            title= "Cycle Tracker"
        }
        val barList = ArrayList<BarEntry>()
        val horizontalBarChart = findViewById<BarChart>(R.id.barChart)
        val db = FirebaseFirestore.getInstance()
        var xValues = mutableListOf<String>()
        var yValues = mutableListOf<Int>()



            FirebaseAuth.getInstance().currentUser?.email?.let {
                db.collection("Period_Tracker").document(
                    it
                ).get().addOnSuccessListener { documentSnapshot ->
                   if(documentSnapshot.exists()){
                       xValues = documentSnapshot.get("dates") as MutableList<String>
                       yValues = documentSnapshot.get("daysbtwn") as MutableList<Int>
                       println("Xvaluesssss $xValues - $yValues")
                       for (i in xValues.indices) {
                           barList.add(BarEntry(i.toFloat(), yValues[i].toFloat()))
                       }
                       val barDataSet = BarDataSet(barList, "Days in Date Range")
                       barDataSet.setColors(ColorTemplate.COLORFUL_COLORS, 130)

                       val data = BarData(barDataSet)

                       horizontalBarChart.data = data
                       horizontalBarChart.description.isEnabled = false
                       horizontalBarChart.setDrawGridBackground(false)
                       horizontalBarChart.setDrawBarShadow(false)
                       horizontalBarChart.setDrawValueAboveBar(true)
                       horizontalBarChart.isHighlightFullBarEnabled = false

                       val yAxis = horizontalBarChart.axisLeft
                       yAxis.setDrawAxisLine(true)
                       yAxis.setDrawGridLines(false)
                       yAxis.axisMinimum = 0f


                       horizontalBarChart.axisRight.isEnabled = false

                       val xAxis = horizontalBarChart.xAxis
                       xAxis.position = XAxis.XAxisPosition.BOTTOM
                       xAxis.setDrawAxisLine(true)
                       xAxis.setDrawGridLines(false)
                       xAxis.valueFormatter = IndexAxisValueFormatter(xValues)


                   }
                    else{
                        println("DocSnapshot doesnot exists")
                   }
                }.addOnFailureListener {
                    println("Error getting doc")
                }
            }


//        val xValues = arrayOf("15Apr-17Apr", "16May-19May", "21Jun-23Jun")
//        val yValues = intArrayOf(30, 40, 60)
        println("Outsideeeee $xValues ")



//        val barDataSet = BarDataSet(barList, "Days in Date Range")
//        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS, 130)
//
//        val data = BarData(barDataSet)
//
//        horizontalBarChart.data = data
//        horizontalBarChart.description.isEnabled = false
//        horizontalBarChart.setDrawGridBackground(false)
//        horizontalBarChart.setDrawBarShadow(false)
//        horizontalBarChart.setDrawValueAboveBar(true)
//        horizontalBarChart.isHighlightFullBarEnabled = false
//
//        val yAxis = horizontalBarChart.axisLeft
//        yAxis.setDrawAxisLine(true)
//        yAxis.setDrawGridLines(false)
//        yAxis.axisMinimum = 0f
//
//
//        horizontalBarChart.axisRight.isEnabled = false
//
//        val xAxis = horizontalBarChart.xAxis
//        xAxis.position = XAxis.XAxisPosition.BOTTOM
//        xAxis.setDrawAxisLine(true)
//        xAxis.setDrawGridLines(false)
//        xAxis.valueFormatter = IndexAxisValueFormatter(xValues)
//        barlist = ArrayList()
//        val xValues = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")
//        val yValues = intArrayOf(10, 20, 30, 40, 50, 60)
//
//        for (i in xValues.indices) {
//
//            barlist.add(BarEntry(yValues[i].toFloat(), xValues[i]))
//        }
//        barlist.add(BarEntry(0 , a[0] , 200f ))
//        barlist.add(BarEntry(2f , 203f ))
//        barlist.add(BarEntry(3f , 207f ))
//        var barDataSet = BarDataSet(barlist , "Cycle length")
//        val barData = BarData(barDataSet)
//        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS , 250)
//        val barChart = findViewById<BarChart>(R.id.barChart)
//        barChart.data = barData
//        barChart.description.isEnabled = false
//        barChart.setDrawGridBackground(false)
//
//        barDataSet.valueTextColor = Color.BLUE
//        barDataSet.valueTextSize = 15f


    }
}




