package com.devlog.androidtestcodeexample.data.model.repository

import com.devlog.androidtestcodeexample.data.model.TodoItem

interface TodoRepository {
    fun getTodoItems(): List<TodoItem>
    fun addTodoItem(item: TodoItem)
    fun removeTodoItem(item: TodoItem)
}