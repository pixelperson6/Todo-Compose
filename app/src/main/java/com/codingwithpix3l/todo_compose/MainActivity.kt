package com.codingwithpix3l.todo_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codingwithpix3l.todo_compose.navigation.SetupNavigation
import com.codingwithpix3l.todo_compose.ui.theme.TodoComposeTheme
import com.codingwithpix3l.todo_compose.ui.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    private val sharedViewModel :SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoComposeTheme {

                navHostController = rememberNavController()
                SetupNavigation(
                    navController = navHostController,
                    sharedViewModel = sharedViewModel

                    )
                
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodoComposeTheme {

    }
}
