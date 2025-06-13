package com.example.gradely.viewmodel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gradely.view.Start
import com.example.gradely.view.StudentHome
import com.example.gradely.view.StudentLanding
import com.example.gradely.view.StudentMarks
import com.example.gradely.view.StudentRegistration
import com.example.gradely.view.StudentTranscript
import com.example.gradely.view.TeacherHome
import com.example.gradely.view.TeacherLanding
import com.example.gradely.view.TeacherMarks

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

        composable(Screens.StudentHome.route) {
            StudentHome(navController)
        }

        composable(Screens.TeacherHome.route) {
            TeacherHome()
        }

        composable(Screens.StudentMarks.route) {
            StudentMarks()
        }

        composable(Screens.TeacherMarks.route) {
            TeacherMarks()
        }

        composable(Screens.StudentRegistration.route) {
            StudentRegistration(
                navController
            )
        }

        composable(Screens.StudentTranscript.route) {
            StudentTranscript()
        }
    }
}