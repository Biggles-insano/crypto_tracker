package com.uvg.mejia.samuel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.uvg.mejia.samuel.presentation.ListScreen
import com.uvg.mejia.samuel.presentation.ProfileScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list_screen") {
        composable("list_screen") {
            ListScreen(navController)
        }
        composable(
            "profile_screen/{assetId}",
            arguments = listOf(navArgument("assetId") { type = NavType.StringType })
        ) { backStackEntry ->
            val assetId = backStackEntry.arguments?.getString("assetId") ?: ""
            ProfileScreen(navController, assetId)
        }
    }
}