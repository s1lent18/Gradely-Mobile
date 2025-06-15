package com.example.gradely

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gradely.model.objects.UserPreferences
import com.example.gradely.ui.theme.GradelyTheme
import com.example.gradely.ui.theme.Lexend
import com.example.gradely.viewmodel.navigation.Screens
import com.example.gradely.viewmodel.viewmodels.StudentTokenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GradelyTheme {
                Splash { startDestination ->
                    startActivity(Intent(this, MainActivity::class.java).apply {
                        putExtra("startDestination", startDestination)
                    })
                    finish()
                }
            }
        }
    }

    @Composable
    private fun Splash(onSplashFinished: (String) -> Unit) {

        val context = LocalContext.current
        var isSplashComplete by remember { mutableStateOf(false) }
        var userRole by remember { mutableStateOf<String?>(null) }
        val studentTokenViewModel = hiltViewModel<StudentTokenViewModel>()
        val studentData by studentTokenViewModel.studentData.collectAsStateWithLifecycle()
        val sessionStatus by studentTokenViewModel.session.collectAsStateWithLifecycle()
        val text = "Gradely"
        var displayedText by remember { mutableStateOf("") }

        LaunchedEffect(Unit) {
            UserPreferences.getUserRole(context).collect {
                userRole = it
            }
        }

        LaunchedEffect (Unit) {

            for (i in 1 ..text.length) {
                displayedText = text.substring(0, i)
                delay(100)
            }

            delay(500)
            isSplashComplete = true
        }

        LaunchedEffect(isSplashComplete, !sessionStatus, userRole) {
            if (isSplashComplete && !sessionStatus && userRole != null) {
                val startDestination = when {
                    studentData?.token!!.isNotEmpty() -> {
                        if (userRole == "Student") Screens.StudentHome.route else Screens.TeacherHome.route
                    }

                    else -> {
                        if (userRole == "Student") Screens.StudentLanding.route else Screens.TeacherLanding.route
                    }
                }
                onSplashFinished(startDestination)
            }
            else if (isSplashComplete && userRole == null) {
                onSplashFinished(Screens.Start.route)
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = displayedText,
                fontSize = 45.sp,
                color = Color.White,
                fontFamily = Lexend,
                fontWeight = FontWeight.Bold
            )
        }
    }
}