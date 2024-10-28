package com.devlog.androidtestcodeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.devlog.androidtestcodeexample.data.model.TodoItem
import com.devlog.androidtestcodeexample.presentation.todo.TodoListScreen
import com.devlog.androidtestcodeexample.presentation.todo.TodoListViewModel
import com.devlog.androidtestcodeexample.ui.theme.AndroidTestCodeExampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Hilt로 주입된 컴포넌트 사용을 위해 필수
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                TodoListScreen() // TodoListScreen 호출
             }

        }
    }
}
