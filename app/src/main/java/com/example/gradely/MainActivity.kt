package com.example.gradely

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.gradely.model.objects.UserPreferences
import com.example.gradely.ui.theme.GradelyTheme
import com.example.gradely.viewmodel.navigation.NavGraph
import com.example.gradely.viewmodel.navigation.Screens
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val startDestination = intent.getStringExtra("startDestination") ?: Screens.Start.route

        enableEdgeToEdge()

        setContent {
            GradelyTheme {
                val navController = rememberNavController()

                NavGraph(
                    navController = navController,
                    startDestination = startDestination,
//                    onRoleSelected = { role ->
//                        lifecycleScope.launch {
//                            UserPreferences.saveUserRole(applicationContext, role)
//                        }
//                    }
                )
            }
        }
    }
}