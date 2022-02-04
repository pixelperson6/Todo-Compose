package com.codingwithpix3l.todo_compose.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.codingwithpix3l.todo_compose.data.model.TodoTask
import com.codingwithpix3l.todo_compose.util.Action

@Composable
fun TaskScreen(
    navigateToListScreen : (action: Action) -> Unit,
    selectedTask: TodoTask?
){

    Scaffold(
        topBar = {
                 TaskAppBar(
                     selectedTask = selectedTask,
                     navigateToListScreen = navigateToListScreen
                 )

        },
        content = {

        }
    )
}