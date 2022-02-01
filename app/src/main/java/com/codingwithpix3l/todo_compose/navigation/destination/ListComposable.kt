package com.codingwithpix3l.todo_compose.navigation.destination


import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.codingwithpix3l.todo_compose.ui.screens.list.ListScreen
import com.codingwithpix3l.todo_compose.ui.viewmodels.SharedViewModel
import com.codingwithpix3l.todo_compose.util.Constant.LIST_ARGUMENT_KEY
import com.codingwithpix3l.todo_compose.util.Constant.LIST_SCREEN


@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId:Int) -> Unit,
    sharedViewModel : SharedViewModel
){
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY){
            type = NavType.StringType
        })
    ){
        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel =sharedViewModel

            )
    }
}