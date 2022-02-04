package com.codingwithpix3l.todo_compose.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.codingwithpix3l.todo_compose.data.model.Priority
import com.codingwithpix3l.todo_compose.data.model.TodoTask
import com.codingwithpix3l.todo_compose.ui.viewmodels.SharedViewModel
import com.codingwithpix3l.todo_compose.util.Action

@Composable
fun TaskScreen(
    navigateToListScreen: (action: Action) -> Unit,
    selectedTask: TodoTask?,
    sharedViewModel: SharedViewModel
) {

    val title :String by sharedViewModel.title
    val description:String by sharedViewModel.description
    val priority:Priority by sharedViewModel.priority


    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen
            )

        },
        content = {

            TaskContent(
                title = title,
                onTitleChanged = {
                    sharedViewModel.updateTitle(it)
                },
                description = description,
                onDescriptionChanged = {
                    sharedViewModel.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModel.priority.value = it
                }
            )

        }
    )
}