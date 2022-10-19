package com.example.myapplication.entity

class VerticalEntity(
    val lineNumber: String,
    val items: Array<Int>,
    var changeNumberCallback: ((Int, Int) -> Unit)? = null
)