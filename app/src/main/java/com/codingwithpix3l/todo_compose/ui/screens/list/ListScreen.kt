package com.codingwithpix3l.todo_compose.ui.screens.list

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.codingwithpix3l.todo_compose.R

@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit
){
    Scaffold(
        content = {},
        floatingActionButton = {
            ListFab(navigateToTaskScreen = navigateToTaskScreen)
        }
    )

}


@Composable
fun ListFab(navigateToTaskScreen: (Int) -> Unit){

    FloatingActionButton(onClick = {
        navigateToTaskScreen(-1)
    }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White
        )
    }

}


@Composable
@Preview
fun PreviewListScreen(){
    ListScreen(navigateToTaskScreen = {})
}