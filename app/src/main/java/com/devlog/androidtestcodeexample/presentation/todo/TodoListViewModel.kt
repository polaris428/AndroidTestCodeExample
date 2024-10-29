package com.devlog.androidtestcodeexample.presentation.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devlog.androidtestcodeexample.data.model.TodoItem
import com.devlog.androidtestcodeexample.data.model.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository // Repository를 주입 받음
) : ViewModel() {

    private val _todoList = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoList: StateFlow<List<TodoItem>> get() = _todoList

    init {
        _todoList.value = repository.getTodoItems()
    }

    fun addTodoItem(title: String) {
        val newItem = TodoItem(id = _todoList.value?.size ?: 0, title = title)
        repository.addTodoItem(newItem)
        _todoList.value = repository.getTodoItems().toList()
    }

    fun removeTodoItem(item: TodoItem) {
        repository.removeTodoItem(item)
        _todoList.value = repository.getTodoItems().toList()
    }
}