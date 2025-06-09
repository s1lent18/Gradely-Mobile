package com.example.gradely.view

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gradely.ui.theme.IBMPlex
import com.example.gradely.ui.theme.Lexend
import com.example.gradely.ui.theme.button
import com.example.gradely.ui.theme.buttonDark
import com.example.gradely.ui.theme.buttonLight
import com.example.gradely.viewmodel.navigation.Screens

@Composable
fun StudentLanding(
    navController: NavController
) {
    Surface {

        val (email, setEmail) = remember { mutableStateOf("") }
        val (password, setPassword) = remember { mutableStateOf("") }
        val color = if (isSystemInDarkTheme()) buttonDark else buttonLight

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConstraintLayout (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp),
            ) {
                val (title, loginField) = createRefs()

                Row (
                    modifier = Modifier.constrainAs(title) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(loginField.top, margin = 50.dp)
                    }.fillMaxWidth(fraction = 0.9f).height(50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        Icons.Default.School,
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )

                    AddWidth(10.dp)

                    Text("Gradely", fontFamily = IBMPlex, fontSize = 35.sp)
                }

                Column (
                    modifier = Modifier.constrainAs(loginField) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth(fraction = 0.9f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text("Your email address", fontFamily = Lexend)
                    }

                    AddHeight(20.dp)

                    Input(
                        label = "ali@uni.com",
                        value = email,
                        onValueChange = setEmail,
                        color = color
                    )

                    AddHeight(20.dp)

                    Row (
                        modifier = Modifier.fillMaxWidth(fraction = 0.9f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text("Your password", fontFamily = Lexend)
                    }

                    AddHeight(20.dp)

                    Input(
                        label = "password",
                        value = password,
                        onValueChange = setPassword,
                        color = color
                    )

                    AddHeight(40.dp)

                    Button(
                        modifier = Modifier.fillMaxWidth(fraction = 0.9f).height(50.dp),
                        shape = RoundedCornerShape(20.dp),
                        onClick = {
                            navController.navigate(Screens.StudentHome.route)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = button,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Login", fontFamily = Lexend)
                    }
                }
            }
        }
    }
}