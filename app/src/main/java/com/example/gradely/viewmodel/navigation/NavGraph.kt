package com.example.gradely.viewmodel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gradely.view.Start
import com.example.gradely.view.StudentLanding
import com.example.gradely.view.TeacherLanding

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String,
    onRoleSelected: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screens.Start.route) {
            Start(onRoleSelected = onRoleSelected)
        }

        composable(Screens.StudentLanding.route) {
            StudentLanding(navController = navController)
        }

        composable(Screens.TeacherLanding.route) {
            TeacherLanding()
        }
    }
}