package com.codingwithpix3l.todo_compose.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.codingwithpix3l.todo_compose.navigation.destination.listComposable
import com.codingwithpix3l.todo_compose.navigation.destination.taskComposable
import com.codingwithpix3l.todo_compose.ui.viewmodels.SharedViewModel
import com.codingwithpix3l.todo_compose.util.Constant.LIST_SCREEN


@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navController:NavHostController,
    sharedViewModel : SharedViewModel
){

    val screens = remember(navController){
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = LIST_SCREEN ){
        listComposable(
            navigateToTaskScreen = screens.task,
            sharedViewModel =sharedViewModel
        )

        taskComposable(
            navigateToListScreen = screens.list
        )
    }

}