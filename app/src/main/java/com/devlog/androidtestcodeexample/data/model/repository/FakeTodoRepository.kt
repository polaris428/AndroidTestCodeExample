package com.devlog.androidtestcodeexample.data.model.repository

import com.devlog.androidtestcodeexample.data.model.TodoItem

class FakeTodoRepository  constructor(): TodoRepository {
    private val todoItems = mutableListOf<TodoItem>()

    override fun getTodoItems(): List<TodoItem> = todoItems

    override fun addTodoItem(item: TodoItem) {
        todoItems.add(item)
    }

    override fun removeTodoItem(item: TodoItem) {
        todoItems.remove(item)
    }
}