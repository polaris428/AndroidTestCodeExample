package com.devlog.androidtestcodeexample.data.model

data class TodoItem(
    val id: Int,
    val title: String,
    var isCompleted: Boolean = false
)