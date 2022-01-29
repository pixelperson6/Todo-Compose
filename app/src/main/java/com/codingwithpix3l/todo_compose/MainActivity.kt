package com.codingwithpix3l.todo_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codingwithpix3l.todo_compose.navigation.SetupNavigation
import com.codingwithpix3l.todo_compose.ui.theme.TodoComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoComposeTheme {

                navHostController = rememberNavController()
                SetupNavigation(navController = navHostController)
                
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
