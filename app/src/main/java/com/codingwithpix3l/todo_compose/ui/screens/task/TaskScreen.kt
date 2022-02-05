package com.codingwithpix3l.todo_compose.ui.screens.task

import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
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


    val context = LocalContext.current
    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen ={ action ->

                    if (action == Action.NO_ACTION){
                        navigateToListScreen(action)
                    }else{
                        if (sharedViewModel.isValid()){
                            navigateToListScreen(action)
                        }else{
                            Toast.makeText(context, "Empty field", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
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