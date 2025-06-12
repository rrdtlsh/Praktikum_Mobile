package com.example.modul5.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.modul5.presentation.navigation.Navigation
import com.example.modul5.presentation.screen.MakeupDetailScreen
import com.example.modul5.presentation.screen.MakeupListScreen
import com.example.modul5.ui.theme.Modul5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.ROUTE_LIST,
    ) {
        composable(Navigation.ROUTE_LIST) {
            MakeupListScreen(navController = navController)
        }
        composable(
            route = Navigation.ROUTE_DETAIL,
            arguments = listOf(navArgument(Navigation.ARG_MAKEUP_ID) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val makeupId = backStackEntry.arguments!!.getInt(Navigation.ARG_MAKEUP_ID)
            MakeupDetailScreen(navController = navController, makeupId = makeupId)
        }
    }
}