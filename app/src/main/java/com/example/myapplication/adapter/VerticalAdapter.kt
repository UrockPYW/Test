package com.example.myapplication.adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.entity.VerticalEntity
import java.util.LinkedList
import kotlin.random.Random

class VerticalAdapter(private val verticalList: LinkedList<VerticalEntity>) :
    RecyclerView.Adapter<VerticalAdapter.TableViewHolder>() {

    private val handler = Handler(Looper.getMainLooper())

    fun init() {
        notifyDataSetChanged()
    }

    fun moveItem(currentPosition: Int, newPosition: Int) {
        val x = verticalList[currentPosition]
        verticalList.removeAt(currentPosition)
        verticalList.add(newPosition, x)

        if (currentPosition < newPosition) {
            Handler(Looper.getMainLooper()).post {
                notifyItemRangeChanged(currentPosition, newPosition - currentPosition + 1)
            }
        } else {
            handler.post {
                notifyItemRangeChanged(newPosition, currentPosition - newPosition + 1)
            }
        }
    }

    fun changeNumber() {
        for (verticalItem in verticalList) {
            val position = Random.nextInt(0, 10)
            val randomNumber = Random.nextInt(0, 1001)
            verticalItem.changeNumberCallback?.invoke(position, randomNumber)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder =
        TableViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.vertical_item, parent, false)
        )

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        verticalList[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = verticalList.size

    inner class TableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val horizontalAdapter = HorizontalAdapter()
        private val lineNumberTV = itemView.findViewById<TextView>(R.id.lineNumber)

        init {
            itemView.findViewById<RecyclerView>(R.id.horizontalRV).apply {
                setHasFixedSize(true)
                adapter = horizontalAdapter
            }
        }

        fun bind(verticalEntity: VerticalEntity) {
            lineNumberTV.text = verticalEntity.lineNumber
            verticalEntity.changeNumberCallback = { position, item ->
                horizontalAdapter.changeItem(position, item)
            }
            horizontalAdapter.set(verticalEntity.items)
        }
    }
}
