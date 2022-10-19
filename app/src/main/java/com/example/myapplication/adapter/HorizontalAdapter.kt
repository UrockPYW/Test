package com.example.myapplication.adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class HorizontalAdapter : RecyclerView.Adapter<HorizontalAdapter.LineViewHolder>() {

    private val horizontalArray: ArrayList<Int> = ArrayList(10)
    private val handler = Handler(Looper.getMainLooper())

    fun set(newArray: Array<Int>) {
        horizontalArray.clear()
        horizontalArray.addAll(newArray)
        notifyDataSetChanged()
    }

    fun changeItem(position: Int, item: Int) {
        horizontalArray[position] = item
        handler.post {
            notifyItemChanged(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder =
        LineViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.horizontal_item, parent, false)
        )

    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {
        horizontalArray[position].let { value -> holder.bind(value) }
    }

    override fun getItemCount(): Int = horizontalArray.size

    class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(value: Int) {
            (itemView as TextView).text = value.toString()
        }
    }
}