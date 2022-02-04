package com.codingwithpix3l.todo_compose.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.codingwithpix3l.todo_compose.R
import com.codingwithpix3l.todo_compose.components.PriorityDropDown
import com.codingwithpix3l.todo_compose.data.model.Priority
import com.codingwithpix3l.todo_compose.ui.theme.LARGE_PADDING
import com.codingwithpix3l.todo_compose.ui.theme.MEDIUM_PADDING

@Composable
fun TaskContent(
    title:String,
    onTitleChanged :(title:String) -> Unit,
    description:String,
    onDescriptionChanged :(disc:String) ->Unit,
    priority:Priority,
    onPrioritySelected:(priority:Priority) ->Unit
){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
        .padding(MEDIUM_PADDING)) {

        OutlinedTextField(
            modifier= Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { onTitleChanged(it) },
            label = { Text(text = stringResource(id = R.string.title))},
            textStyle = MaterialTheme.typography.body1,
            singleLine = true
        )
        Divider(
            modifier = Modifier.height(MEDIUM_PADDING),
            color = MaterialTheme.colors.background
        )
        PriorityDropDown(
            priority = priority,
            onPriorityClicked = onPrioritySelected)
        OutlinedTextField(
            modifier= Modifier.fillMaxSize(),
            value = description,
            onValueChange = { onDescriptionChanged(it) },
            label = { Text(text = stringResource(id = R.string.description))},
            textStyle = MaterialTheme.typography.body1
        )




    }

}