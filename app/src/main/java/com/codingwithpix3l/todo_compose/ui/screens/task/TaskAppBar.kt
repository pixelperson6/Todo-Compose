package com.codingwithpix3l.todo_compose.ui.screens.task

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.codingwithpix3l.todo_compose.R
import com.codingwithpix3l.todo_compose.data.model.TodoTask
import com.codingwithpix3l.todo_compose.ui.theme.topAppbarBackgroundColor
import com.codingwithpix3l.todo_compose.ui.theme.topAppbarContentColor
import com.codingwithpix3l.todo_compose.util.Action

@Composable
fun TaskAppBar(
    selectedTask: TodoTask?,
    navigateToListScreen : (action:Action) -> Unit,
    ) {

    if (selectedTask==null){
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    }else{
        ExistingTaskAppBar(
            selectedTodoTask = selectedTask,
            navigateToListScreen = navigateToListScreen )
    }

}

@Composable
fun NewTaskAppBar(
    navigateToListScreen : (action:Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            BackAction(onBackPressed = navigateToListScreen)
        },
        title = {
            Text(
                text = stringResource(R.string.add_task),
                color = MaterialTheme.colors.topAppbarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppbarBackgroundColor,
        actions = {
            UpdateAction(onUpdatePressed = navigateToListScreen)
        }
    )
}

@Composable
fun ExistingTaskAppBar(
    selectedTodoTask: TodoTask,
    navigateToListScreen : (action:Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            CloseAction(onClosePressed = navigateToListScreen)
        },
        title = {
            Text(
                text = selectedTodoTask.title,
                color = MaterialTheme.colors.topAppbarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.topAppbarBackgroundColor,
        actions = {
            DeleteAction(onDeletePressed = navigateToListScreen)
            UpdateAction(onUpdatePressed = navigateToListScreen)
        }
    )
}

@Composable
fun BackAction(
    onBackPressed: (action: Action) -> Unit
) {
    IconButton(onClick = { onBackPressed(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.back_button),
            tint = MaterialTheme.colors.topAppbarContentColor
        )
    }
}

@Composable
fun CloseAction(
    onClosePressed: (action: Action) -> Unit
) {
    IconButton(onClick = { onClosePressed(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = stringResource(R.string.close_btn),
            tint = MaterialTheme.colors.topAppbarContentColor
        )
    }
}

@Composable
fun AddAction(
    onAddPressed: (action: Action) -> Unit
) {
    IconButton(onClick = { onAddPressed(Action.ADD) }) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = stringResource(id = R.string.add_task),
            tint = MaterialTheme.colors.topAppbarContentColor
        )
    }
}

@Composable
fun UpdateAction(
    onUpdatePressed: (action: Action) -> Unit
) {
    IconButton(onClick = { onUpdatePressed(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = stringResource(id = R.string.update_task),
            tint = MaterialTheme.colors.topAppbarContentColor
        )
    }
}

@Composable
fun DeleteAction(
    onDeletePressed: (action: Action) -> Unit
) {
    IconButton(onClick = { onDeletePressed(Action.DELETE) }) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(id = R.string.delete_task),
            tint = MaterialTheme.colors.topAppbarContentColor
        )
    }
}