package com.devlog.todo


import com.devlog.androidtestcodeexample.data.model.TodoItem
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devlog.androidtestcodeexample.data.model.repository.FakeTodoRepository
import com.devlog.androidtestcodeexample.presentation.todo.TodoListViewModel
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.runner.RunWith


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class TodoListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule() // LiveData를 테스트할 때 필요

    private lateinit var viewModel: TodoListViewModel

    private val fakeRepository = FakeTodoRepository()
    @Before
    fun setup() {
        viewModel = TodoListViewModel(fakeRepository)
    }

    @Test
    fun addTodoItem_itemAddedSuccessfully() {
        // Given


        // When
        viewModel.addTodoItem("New Task")

        // Then
        val todoList = viewModel.todoList.value ?: emptyList()
        assertEquals(1, todoList.size)
        assertEquals("New Task", todoList[0].title)
    }

    @Test
    fun removeTodoItem_itemRemovedSuccessfully() {
        // Given
        val item = TodoItem(id = 0, title = "New Task")
        viewModel.addTodoItem("New Task")

        // When
        viewModel.removeTodoItem(item)

        // Then
        val todoList = viewModel.todoList.value ?: emptyList()
        assertEquals(0, todoList.size)
    }
}