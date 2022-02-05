package com.codingwithpix3l.todo_compose.ui.screens.list


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.codingwithpix3l.todo_compose.R
import com.codingwithpix3l.todo_compose.ui.theme.fabBackgroundColor
import com.codingwithpix3l.todo_compose.ui.viewmodels.SharedViewModel
import com.codingwithpix3l.todo_compose.util.SearchBarState


@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel : SharedViewModel
) {

    LaunchedEffect(key1 = true){
        sharedViewModel.getAllTasks()
    }

    val action by sharedViewModel.action

    val allTask by sharedViewModel.allTasks.collectAsState()

    val searchBarState :SearchBarState by sharedViewModel.searchBarState
    val searchTextState :String by sharedViewModel.searchTextState

    sharedViewModel.handleDatabaseAction(action = action)

    Scaffold(
        topBar = {
                 TopAppBar(
                     sharedViewModel = sharedViewModel,
                     searchBarState = searchBarState,
                     searchTextState = searchTextState
                     )
        },
        content = {
            ListContent(
                tasks = allTask,
                navigateToTaskScreen = navigateToTaskScreen
            )
        },
        floatingActionButton = {
            ListFab(OnFabClicked = navigateToTaskScreen)
        }
    )
}


@Composable
fun ListFab(OnFabClicked: (taskId: Int) -> Unit) {
    FloatingActionButton(
        onClick = {
            OnFabClicked(-1)
        },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White
        )
    }
}
