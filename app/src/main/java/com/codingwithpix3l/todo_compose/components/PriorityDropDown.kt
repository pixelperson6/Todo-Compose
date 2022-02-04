package com.codingwithpix3l.todo_compose.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codingwithpix3l.todo_compose.R
import com.codingwithpix3l.todo_compose.data.model.Priority
import com.codingwithpix3l.todo_compose.ui.theme.PRIORITY_DROP_DOWN_HEIGHT
import com.codingwithpix3l.todo_compose.ui.theme.PRIORITY_INDICATOR_SIZE
import com.codingwithpix3l.todo_compose.ui.theme.Typography

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPriorityClicked: (priority: Priority) -> Unit
) {

    var expandedState by remember { mutableStateOf(false) }

    val angle by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .height(PRIORITY_DROP_DOWN_HEIGHT)
            .clickable { expandedState = true }
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                shape = MaterialTheme.shapes.small
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier
                .size(PRIORITY_INDICATOR_SIZE)
                .weight(weight = 1f)
        ) {
            drawCircle(color = priority.color)
        }
        Text(
            modifier = Modifier.weight(weight = 8f),
            text = priority.name,
            style = Typography.subtitle1
        )
        IconButton(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .rotate(degrees = angle)
                .weight(weight = 1.5f),
            onClick = { expandedState = !expandedState }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = stringResource(R.string.dropdown_arrow)
            )

        }
        DropdownMenu(modifier = Modifier
            .fillMaxWidth(fraction = 0.94f)
            .background(MaterialTheme.colors.background),
            expanded = expandedState,
            onDismissRequest = { expandedState = false }
        ) {
            DropdownMenuItem(onClick = {
                expandedState = false
                onPriorityClicked(Priority.LOW)
            }) {
                PriorityItem(priority = Priority.LOW)
            }
            DropdownMenuItem(onClick = {
                expandedState = false
                onPriorityClicked(Priority.MEDIUM)
            }) {
                PriorityItem(priority = Priority.MEDIUM)
            }
            DropdownMenuItem(onClick = {
                expandedState = false
                onPriorityClicked(Priority.HIGH)
            }) {
                PriorityItem(priority = Priority.HIGH)
            }
        }
    }
}

@Composable
@Preview
fun PriorityDropDownPreview(){
    PriorityDropDown(Priority.HIGH) {}
}