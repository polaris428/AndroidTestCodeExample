package com.devlog.androidtestcodeexample.todo


import android.util.Log
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import org.junit.Before
import org.junit.Rule
import org.junit.Test


import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devlog.androidtestcodeexample.MainActivity

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class TodoListScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testAddTodoItem_displaysInList() {
        composeTestRule.onNodeWithText("New Todo").performTextInput("Test Task")
        composeTestRule.onNodeWithText("Add Todo").performClick()

        // Verifying that "Test Task" appears in the list after adding
        composeTestRule.onNodeWithText("Test Task").assertExists()
    }

    @Test
    fun testRemoveTodoItem_removesFromList() {
        composeTestRule.onNodeWithText("New Todo").performTextInput("Task to Remove")
        composeTestRule.onNodeWithText("Add Todo").performClick()

        // Check if the item is added
        composeTestRule.onNodeWithText("Task to Remove").assertExists()

        // Locate and click the delete icon associated with "Task to Remove"
        composeTestRule.onNodeWithContentDescription("Delete").performClick()

        // Verify the item is no longer in the list
        composeTestRule.onNodeWithText("Task to Remove").assertDoesNotExist()
    }

    @Test
    fun testRemoveTodoItem_removesFromListMulti() {
        for (i in 0..5){
            composeTestRule.onNodeWithText("New Todo").performTextInput("Task to Remove")
            composeTestRule.onNodeWithText("Add Todo").performClick()
        }

        for (j in 0..5){
            composeTestRule.onAllNodesWithText("Task to Remove")[j].assertExists()
        }

        repeat(6) {
            composeTestRule.onAllNodesWithContentDescription("Delete")[0].performClick()
        }

        composeTestRule.onNodeWithText("Task to Remove").assertDoesNotExist()
        // Locate and click the delete icon associated with "Task to Remove"


        // Verify the item is no longer in the list

    }

    @Test
    fun testAddEmptyTodoItem_doesNotDisplayInList() {
        // 현재 Todo 목록 항목 개수 확인
        val initialItemCount = composeTestRule.onAllNodesWithText("Test Task").fetchSemanticsNodes().size
        composeTestRule.onNodeWithText("Task to Remove").assertDoesNotExist()

        // 빈 텍스트로 Todo 추가 시도
        composeTestRule.onNodeWithText("New Todo").performTextInput("")
        composeTestRule.onNodeWithText("Add Todo").performClick()

        composeTestRule.onNodeWithText("New Todo").performTextInput("Test Task")
        composeTestRule.onNodeWithText("Add Todo").performClick()
        composeTestRule.onNodeWithText("Test Task").assertExists()
        val finalItemCount =composeTestRule.onAllNodesWithText("Test Task").fetchSemanticsNodes().size
        // Todo 항목 개수가 변경되지 않았는지 검증
        assert(initialItemCount == finalItemCount) { "빈 항목이 추가되지 않아야 합니다." }

        Log.e("polaris",initialItemCount.toString())
        Log.e("polarisf", finalItemCount.toString())

    }


}