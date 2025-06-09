package com.example.gradely.viewmodel.navigation

sealed class Screens(val route: String) {
    data object StudentHome: Screens("studentHomeScreen")
    data object TeacherHome: Screens("teacherHomeScreen")
    data object Start: Screens("startScreen")
    data object StudentLanding: Screens("studentLandingScreen")
    data object TeacherLanding: Screens("teacherLandingScreen")
}
