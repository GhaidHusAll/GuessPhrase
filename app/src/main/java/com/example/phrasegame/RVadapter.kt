package com.example.phrasegame

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rows.view.*

class RVadapter(private val Guesses : ArrayList<String>,private val Guesses2 : ArrayList<Int>): RecyclerView.Adapter<RVadapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(
           LayoutInflater.from(parent.context).inflate(
               R.layout.rows,
               parent,
               false
           )
       )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = Guesses[position]
        val isRight = Guesses2[position]

        holder.itemView.apply {
            when (isRight) {
                1 -> {
                    //right
                    tvRowItem.setTextColor(Color.GREEN)
                }
                0 -> {
                    //wrong
                    tvRowItem.setTextColor(Color.RED)
                }
                else -> {
                    //normal
                    tvRowItem.setTextColor(Color.BLACK)
                }
            }
            tvRowItem.text = item
        }
    }

    override fun getItemCount() = Guesses.size
}