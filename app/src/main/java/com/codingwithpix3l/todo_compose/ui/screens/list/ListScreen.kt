package com.codingwithpix3l.todo_compose.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.codingwithpix3l.todo_compose.R
import com.codingwithpix3l.todo_compose.ui.theme.topAppbarBackgroundColor
import com.codingwithpix3l.todo_compose.ui.theme.topAppbarContentColor

@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit
){
    Scaffold(
        content = {},
        topBar = {
                 TopAppBar(
                     title ={

                         Text(
                             text = "Tasks",
                             color = MaterialTheme.colors.topAppbarContentColor
                             )

                     } ,

                 backgroundColor = MaterialTheme.colors.topAppbarBackgroundColor

                 )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigateToTaskScreen(-1)
            }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.add_button),
                    tint = Color.White
                )
            }
          //  ListFab(OnFabClicked = navigateToTaskScreen)
        }
    )

}


@Composable
fun ListFab(OnFabClicked: (Int) -> Unit){

    FloatingActionButton(onClick = {
        OnFabClicked(-1)
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