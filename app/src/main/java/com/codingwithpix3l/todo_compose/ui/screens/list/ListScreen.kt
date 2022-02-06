package com.codingwithpix3l.todo_compose.ui.screens.list


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.codingwithpix3l.todo_compose.R
import com.codingwithpix3l.todo_compose.ui.theme.fabBackgroundColor
import com.codingwithpix3l.todo_compose.ui.viewmodels.SharedViewModel
import com.codingwithpix3l.todo_compose.util.Action
import com.codingwithpix3l.todo_compose.util.SearchBarState
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {

    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
    }

    val action by sharedViewModel.action

    val allTask by sharedViewModel.allTasks.collectAsState()

    val searchBarState: SearchBarState by sharedViewModel.searchBarState
    val searchTextState: String by sharedViewModel.searchTextState


    val scaffoldState = rememberScaffoldState()
    DisplaySnackBar(
        scaffoldState = scaffoldState,
        handleDatabaseAction = { sharedViewModel.handleDatabaseAction(action = action) },
        onUndoClicked = {
            sharedViewModel.action.value = it
        },
        taskTitle = sharedViewModel.title.value,
        action = action
    )

    Scaffold(
        scaffoldState = scaffoldState,
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

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    handleDatabaseAction: () -> Unit,
    onUndoClicked: (action: Action) -> Unit,
    taskTitle: String,
    action: Action
) {
    handleDatabaseAction()
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = "${action.name}: $taskTitle",
                    actionLabel = if (action.name == "DELETE") "UNDO" else "OK"
                )
                undoDeletedTask(
                    action = action,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )
            }
        }
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (action: Action) -> Unit,
) {
    if (snackBarResult == SnackbarResult.ActionPerformed
        && action == Action.DELETE
    ) {
        onUndoClicked(Action.UNDO)
    }

}