package com.codingwithpix3l.todo_compose.navigation


import androidx.navigation.NavHostController
import com.codingwithpix3l.todo_compose.util.Action
import com.codingwithpix3l.todo_compose.util.Constant.LIST_SCREEN

class Screens(navController: NavHostController) {

    val list : (Action) -> Unit = { action ->

        navController.navigate("list/${action.name}"){
            popUpTo(LIST_SCREEN){ inclusive = true }
        }

    }

    val task : (Int) -> Unit = { taskId ->
        navController.navigate("task/${taskId}")
    }
}