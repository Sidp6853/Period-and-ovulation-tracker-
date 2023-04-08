package com.example.potapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.potapp.Mood
import com.example.potapp.R
import com.example.potapp.Symptoms

class Mood_Hori_RecyclerView(private val MoodList : List<Mood>): RecyclerView.Adapter<Mood_Hori_RecyclerView.MyViewHolderNew>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolderNew {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.iteom, parent , false)
        return Mood_Hori_RecyclerView.MyViewHolderNew(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderNew, position: Int) {
        val moods = MoodList[position]
        holder.moodImageView.setImageResource(moods.moodImage)
        holder.moodTextView.text = moods.moodText
    }

    override fun getItemCount(): Int {
        return MoodList.size
    }
    class MyViewHolderNew(itemView : View):RecyclerView.ViewHolder(itemView) {
        val moodImageView: ImageView = itemView.findViewById(R.id.imageviewNew)
        val moodTextView : TextView = itemView.findViewById(R.id.textViewRecyclerNew)

    }
}