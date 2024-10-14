package com.example.swiss.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swiss.ui.activities.ActivitiesView
import com.example.swiss.ui.home.HomeView
import com.example.swiss.ui.theme.SwissTheme
import com.example.swiss.ui.vm.ViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwissTheme() {

                /* val selected = remember { mutableStateOf("home") }

                 val bottomItems = remember {
                     mapOf(
                         "home" to Icons.Rounded.Home,
                         "attività" to Icons.Rounded.DateRange
                     )
                 }*/

                Scaffold(
                    modifier = Modifier.navigationBarsPadding(),
                    /*bottomBar = {
                        BottomNavigation {
                            bottomItems.forEach {
                                BottomNavigationItem(
                                    icon = { Icon(it.value, contentDescription = null) },
                                    selected = it.key == selected.value,
                                    onClick = {
                                        selected.value = it.key
                                    }
                                )
                            }
                        }
                    }*/
                ) { innerPadding ->
                    val navController = rememberNavController()
                    val viewModel = koinViewModel<ViewModel>()
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = "Home"
                    ) {
                        composable("Home") {
                            HomeView(
                                navController,
                                viewModel
                            )
                        }
                        composable("Activities/{userId}") { backstackEntry ->
                            ActivitiesView(
                                backstackEntry.arguments?.getString("userId"),
                                viewModel
                            )
                        }
                    }
                }
            }
        }
    }
}

