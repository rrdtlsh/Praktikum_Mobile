package com.example.modul3

import android.os.Bundle
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
import com.example.modul3.ui.screen.MakeupListScreen
import com.example.modul3.ui.screen.MakeupDetailScreen
import com.example.modul3.ui.theme.Modul3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul3Theme {
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
                MakeupListScreen(navController = navController)
            }
            composable(Navigation.ROUTE_DETAIL) { backStackEntry ->
                val makeupId = backStackEntry.arguments?.getString(Navigation.ARG_MAKEUP_ID) ?: ""
                MakeupDetailScreen(navController = navController, makeupId = makeupId)
            }
        }
    }
}