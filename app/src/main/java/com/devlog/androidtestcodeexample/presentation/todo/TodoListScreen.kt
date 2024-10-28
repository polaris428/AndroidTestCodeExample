package com.devlog.androidtestcodeexample.presentation.todo


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devlog.androidtestcodeexample.data.model.TodoItem


@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel = hiltViewModel() // Hilt로 주입된 ViewModel 사용
) {
    val todoItems by viewModel.todoList.collectAsState(emptyList()) // StateFlow 관찰
    var newItemTitle by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        // 새로운 할 일 추가를 위한 입력 필드
        TextField(
            value = newItemTitle,
            onValueChange = { newItemTitle = it },
            label = { Text("New Todo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // 할 일 추가 버튼
        Button(
            onClick = {
                if (newItemTitle.isNotBlank()) {
                    viewModel.addTodoItem(newItemTitle)
                    newItemTitle = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Todo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 할 일 목록을 LazyColumn으로 표시
        LazyColumn {
            items(todoItems, key = { it.id }) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(text = item.title, modifier = Modifier.weight(1f))
                    IconButton(onClick = { viewModel.removeTodoItem(item) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}