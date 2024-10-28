package com.devlog.todo


import com.devlog.androidtestcodeexample.data.model.TodoItem
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devlog.androidtestcodeexample.data.model.repository.FakeTodoRepository
import com.devlog.androidtestcodeexample.presentation.todo.TodoListViewModel
import com.devlog.androidtestcodeexample.utile.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@ExperimentalCoroutinesApi
class TodoListViewModelTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val instantExecutorRule = InstantTaskExecutorRule()

    // Hilt를 통해 주입된 ViewModel 및 Fake Repository
    @Inject
    lateinit var viewModel: TodoListViewModel

    @Inject
    @Named("fakeRepository")
    lateinit var fakeRepository: FakeTodoRepository

    @Before
    fun setup() {
        hiltRule.inject() // Hilt 주입 설정
    }

    @Test
    fun addTodoItem_updatesTodoList() = runBlockingTest {
        val newItem = TodoItem(id = 1, title = "Test Task")
        viewModel.addTodoItem(newItem.title)

        // 예상대로 아이템이 추가되었는지 확인
        assertEquals(listOf(newItem), viewModel.todoList.value)
    }

    @Test
    fun removeTodoItem_updatesTodoList() = runBlockingTest {
        val newItem = TodoItem(id = 0, title = "Test Task")
        viewModel.addTodoItem(newItem.title)
        viewModel.removeTodoItem(newItem)

        // 예상대로 아이템이 삭제되었는지 확인
        assertEquals(emptyList<TodoItem>(), viewModel.todoList.value)
    }
}