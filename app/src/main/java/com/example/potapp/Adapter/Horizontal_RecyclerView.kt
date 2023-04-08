package com.example.potapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.potapp.OnItemClickListner
import com.example.potapp.R
import com.example.potapp.Symptoms

class Horizontal_RecyclerView(private val SympList : List<Symptoms>): RecyclerView.Adapter<Horizontal_RecyclerView.MyViewHolder>() {


        private var onItemClickListner : OnItemClickListner? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent , false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val symptoms = SympList[position]
        holder.foodImageView.setImageResource(symptoms.sympImage)
        holder.foodTextView.text = symptoms.sympText

        holder.itemView.setOnClickListener{
            onItemClickListner?.onItemClick(it , position)
        }


    }

    override fun getItemCount(): Int {
       return SympList.size
    }
    fun setOnItemClickListner(listner: OnItemClickListner){
        onItemClickListner = listner

    }
    class MyViewHolder(itemView :View):RecyclerView.ViewHolder(itemView) {

        val foodImageView: ImageView = itemView.findViewById(R.id.imageview2)
        val foodTextView : TextView = itemView.findViewById(R.id.textViewRecycler)

    }
}