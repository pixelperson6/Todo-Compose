package com.codingwithpix3l.todo_compose.navigation.destination

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.codingwithpix3l.todo_compose.ui.screens.task.TaskScreen
import com.codingwithpix3l.todo_compose.ui.viewmodels.SharedViewModel
import com.codingwithpix3l.todo_compose.util.Action
import com.codingwithpix3l.todo_compose.util.Constant.TASK_ARGUMENT_KEY
import com.codingwithpix3l.todo_compose.util.Constant.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        }
        )
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)

        sharedViewModel.getSelectedTask(taskId = taskId)
        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask){

            if (selectedTask !=null || taskId == -1){
                sharedViewModel.updateTaskFields(selectedTask = selectedTask)
            }

        }

        TaskScreen(
            navigateToListScreen = navigateToListScreen,
            selectedTask = selectedTask,
            sharedViewModel=sharedViewModel
            )

    }
}