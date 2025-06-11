package com.example.gradely

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.gradely.model.objects.UserPreferences
import com.example.gradely.ui.theme.GradelyTheme
import com.example.gradely.viewmodel.navigation.NavGraph
import com.example.gradely.viewmodel.navigation.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            GradelyTheme {
                val context = LocalContext.current
                val navController = rememberNavController()
                val roleFlow = remember { UserPreferences.getUserRole(context) }
                val roleState = roleFlow.collectAsState(initial = null)
                val savedRole = roleState.value

                var selectedRole by rememberSaveable { mutableStateOf<String?>(null) }

                LaunchedEffect(selectedRole) {
                    selectedRole?.let { role ->
                        UserPreferences.saveUserRole(context, role)
                        navController.navigate(
                            if (role == "Student") Screens.StudentLanding.route
                            else Screens.TeacherLanding.route
                        ) {
                            popUpTo(Screens.Start.route) { inclusive = true }
                        }
                    }
                }

                NavGraph(
                    navController = navController,
                    startDestination = when (savedRole) {
                        "Student" -> Screens.StudentLanding.route
                        "Teacher" -> Screens.TeacherLanding.route
                        else -> Screens.Start.route
                    },
                    onRoleSelected = { role ->
                        selectedRole = role
                    }
                )
            }
        }
    }
}