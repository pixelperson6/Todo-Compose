package com.codingwithpix3l.todo_compose.ui.screens.list


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codingwithpix3l.todo_compose.R
import com.codingwithpix3l.todo_compose.data.model.Priority
import com.codingwithpix3l.todo_compose.data.model.TodoTask
import com.codingwithpix3l.todo_compose.ui.theme.*

@ExperimentalMaterialApi
@Composable
fun ListContent(
    tasks: List<TodoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {

    if (tasks.isEmpty()){
        EmptyContent()
    }else{
        DisplayTasks(
            tasks = tasks,
            navigateToTaskScreen = navigateToTaskScreen
        )
    }

}

@ExperimentalMaterialApi
@Composable
fun DisplayTasks(
    tasks: List<TodoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit){
    LazyColumn{
        items(
            items = tasks,
            key = { task ->
                task.id
            }
        ){  task ->
            TaskItem(
                todoTask = task,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun TaskItem(
    todoTask: TodoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {

    Surface(

        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.taskItemBackground,
        shape = RectangleShape,
        elevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(todoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = LARGE_PADDING)
        ) {
            Row {
                Text(
                    modifier = Modifier
                        .weight(8f),
                    text = todoTask.title,
                    color = MaterialTheme.colors.taskItemContent,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle( color = todoTask.priority.color  )
                    }
                }
            }
            Text(
                modifier=Modifier.fillMaxWidth(),
                text = todoTask.disc,
                color = MaterialTheme.colors.taskItemContent,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}

@Composable
fun EmptyContent(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.ic_sad_emoji),
            contentDescription = stringResource(R.string.sad_face),
            tint = MediumGray
        )
        Text(
            text = stringResource(R.string.empty_content_txt),
            color = MediumGray,
            fontWeight = FontWeight.Bold,
            fontStyle = MaterialTheme.typography.h6.fontStyle
        )

    }
}

@Composable
@Preview
fun TaskItemPreview(){
    EmptyContent()
}

