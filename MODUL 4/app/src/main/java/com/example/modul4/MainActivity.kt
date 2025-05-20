package com.example.modul4

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modul4.ui.screen.MakeupDetailScreen
import com.example.modul4.ui.screen.MakeupListScreen
import com.example.modul4.ui.theme.Modul4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Log.d("MainActivity", "onCreate called - App started")

        setContent {
            Modul4Theme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController: NavHostController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Navigation.ROUTE_LIST,
            modifier = Modifier.padding(padding)
        ) {
            composable(Navigation.ROUTE_LIST) {
                Log.d("Navigation", "Navigated to MakeupListScreen")
                MakeupListScreen(navController = navController)
            }
            composable(Navigation.ROUTE_DETAIL) { backStackEntry ->
                val makeupId = backStackEntry.arguments?.getString(Navigation.ARG_MAKEUP_ID) ?: ""
                Log.d("Navigation", "Navigated to MakeupDetailScreen with ID: $makeupId")
                MakeupDetailScreen(navController = navController, makeupId = makeupId)
            }
        }
    }
}