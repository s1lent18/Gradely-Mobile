package com.example.gradely.viewmodel.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gradely.model.objects.UserPreferences
import com.example.gradely.view.Start
import com.example.gradely.view.StudentHome
import com.example.gradely.view.StudentLanding
import com.example.gradely.view.StudentMarks
import com.example.gradely.view.StudentRegistration
import com.example.gradely.view.StudentTranscript
import com.example.gradely.view.TeacherHome
import com.example.gradely.view.TeacherLanding
import com.example.gradely.view.TeacherMarks
import kotlinx.coroutines.launch

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screens.Start.route) {
            Start { selectedRole ->
                scope.launch {
                    UserPreferences.saveUserRole(context, selectedRole)
                }
                navController.navigate(
                    if (selectedRole == "Student") Screens.StudentLanding.route
                    else Screens.TeacherLanding.route
                ) {
                    popUpTo(Screens.Start.route) { inclusive = true }
                }
            }
        }

        composable(Screens.StudentLanding.route) {
            StudentLanding(navController = navController)
        }

        composable(Screens.TeacherLanding.route) {
            TeacherLanding(navController = navController)
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