package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.VerticalAdapter
import com.example.myapplication.entity.VerticalEntity
import java.util.LinkedList
import kotlin.concurrent.thread
import kotlin.random.Random

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val list = LinkedList<VerticalEntity>().apply {
            for (verticalIndex in 0..99) {
                val array = Array(10) { Random.nextInt(0, 1001) }
                add(VerticalEntity(lineNumber = "#$verticalIndex", items = array))
            }
        }
        val verticalAdapter = VerticalAdapter(verticalList = list)

        findViewById<RecyclerView>(R.id.verticalRV).apply {
            setHasFixedSize(true)
            adapter = verticalAdapter
        }
        verticalAdapter.init()

        thread {
            while (true) {
                Thread.sleep(1000)
                val currentLinePosition = Random.nextInt(0, 100)
                val newLinePosition = Random.nextInt(0, 100)
                verticalAdapter.moveItem(currentLinePosition, newLinePosition)
                verticalAdapter.changeNumber()
            }
        }
    }
}

