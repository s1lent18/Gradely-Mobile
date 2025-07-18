package com.example.gradely.view

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HowToVote
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gradely.model.dataRequests.StudentFCMToken
import com.example.gradely.ui.theme.Lexend
import com.example.gradely.ui.theme.buttonDark
import com.example.gradely.ui.theme.buttonLight
import com.example.gradely.viewmodel.navigation.Screens
import com.example.gradely.viewmodel.viewmodels.StudentAuthViewModel
import com.example.gradely.viewmodel.viewmodels.StudentTokenViewModel
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentHome(
    navController: NavController,
    studentAuthViewModel: StudentAuthViewModel = hiltViewModel(),
    studentTokenViewModel: StudentTokenViewModel = hiltViewModel(),
) {

    val studentData = studentTokenViewModel.studentData.collectAsState().value
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val scope = rememberCoroutineScope()
    var popup by remember { mutableStateOf(false) }

    LaunchedEffect(studentData) {
        Log.d("Check Alert", "$studentData")
        studentData?.let {
            if (it.fcmToken.isEmpty()) {
                popup = true
            }
        }
    }

    if (popup) {
        AlertDialog(
            onDismissRequest = { popup = false },
            title = {
                Text(
                    text = "Device Info",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Lexend
                )
            },
            text = {
                Text(
                    "Allow us to save the device info for notifications",
                    fontSize = 15.sp,
                    fontFamily = Lexend
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    if (studentData != null) {
                        Firebase.messaging.token.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val fcmToken = task.result
                                val studentFCMToken = StudentFCMToken(token = fcmToken)
                                studentAuthViewModel.sendStudentFCMToken(
                                    studentId = studentData.studentId,
                                    token = "Bearer " + studentData.token,
                                    studentFCMToken = studentFCMToken
                                )
                            }
                        }
                    }
                    popup = false
                }) {
                    Text("Allow", fontFamily = Lexend)
                }
            },
            dismissButton = {
                TextButton(onClick = { popup = false }) {
                    Text("Cancel", fontFamily = Lexend)
                }
            }
        )

    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .then(Modifier.fillMaxWidth(fraction = 0.8f))
                    ,
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {

                    AddHeight(50.dp)

                    SideBarItem(
                        icon = Icons.Default.Home,
                        text = "Home",
                        isSelected = true,
                        onClick = {}
                    )
                    AddHeight(8.dp)
                    SideBarItem(
                        icon = Icons.Default.HowToVote,
                        text = "Course Registration",
                        isSelected = false,
                        onClick = {
                            Log.d("Registration Check", studentData?.studentId!!)
                            //studentRegistrationsViewModel.getRegistration(studentId = studentData.studentId, token = "Bearer " + studentData.token)
                            navController.navigate(Screens.StudentRegistration.route)
                        }
                    )
                    AddHeight(8.dp)
                    SideBarItem(
                        icon = Icons.Default.SupervisorAccount,
                        text = "Attendance",
                        isSelected = false,
                        onClick = {
                            navController.navigate(Screens.StudentAttendance.route)
                        }
                    )
                    AddHeight(8.dp)
                    SideBarItem(
                        icon = Icons.Default.CheckCircleOutline,
                        text = "Marks",
                        isSelected = false,
                        onClick = {
                            navController.navigate(Screens.StudentMarks.route)
                        }
                    )
                    AddHeight(8.dp)
                    SideBarItem(
                        icon = Icons.Default.FileCopy,
                        text = "Transcript",
                        isSelected = false,
                        onClick = {}
                    )
                    AddHeight(8.dp)
                    SideBarItem(
                        icon = Icons.Default.FileOpen,
                        text = "Course Feedback",
                        isSelected = false,
                        onClick = {}
                    )
                    AddHeight(8.dp)
                    SideBarItem(
                        icon = Icons.Default.FileOpen,
                        text = "Retake Exam Request",
                        isSelected = false,
                        onClick = {}
                    )
                    AddHeight(8.dp)
                    SideBarItem(
                        icon = Icons.Default.FileOpen,
                        text = "Course Withdraw Request",
                        isSelected = false,
                        onClick = {}
                    )
                    AddHeight(8.dp)
                    SideBarItem(
                        icon = Icons.Default.FileOpen,
                        text = "Grade Change Request",
                        isSelected = false,
                        onClick = {}
                    )
                }
            }
        }
    ) {
        Scaffold (
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                Appbar("Course Registration", openDrawer = {
                    scope.launch { drawerState.open() }
                }, onLogout = {
                    studentTokenViewModel.logout()
                    navController.navigate(Screens.StudentLanding.route) {
                        popUpTo(0)
                    }
                })
            }
        ) { values ->
            ConstraintLayout (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values)
            ) {

                val (info, scroll) = createRefs()

                Row (
                    modifier = Modifier
                        .constrainAs(info) {
                            top.linkTo(parent.top, margin = 50.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .fillMaxWidth(fraction = 0.9f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text("Student Profile", fontFamily = Lexend, fontSize = 20.sp)
                }

                LazyColumn (
                    modifier = Modifier.constrainAs(scroll) {
                        top.linkTo(info.bottom, margin = 30.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.percent(0.9f)
                        bottom.linkTo(parent.bottom, margin = 30.dp)
                        height = Dimension.fillToConstraints
                    },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        ElevatedCard (
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = CardDefaults.elevatedCardColors(
                                containerColor = if (isSystemInDarkTheme()) buttonDark else buttonLight,
                                contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
                            ),
                            shape = RoundedCornerShape(10.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 10.dp
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text("Email: ${studentData?.assignedEmail}", fontSize = 12.sp, fontFamily = Lexend)
                                AddHeight(6.dp)
                                Text("Degree: ${studentData?.degree}", fontSize = 12.sp, fontFamily = Lexend)
                                AddHeight(6.dp)
                                Text("Batch: Fall ${studentData?.batch}", fontSize = 12.sp, fontFamily = Lexend)
                                AddHeight(6.dp)
                                Text("Status: ${studentData?.status}", fontSize = 12.sp, fontFamily = Lexend)
                            }
                        }
                        AddHeight(20.dp)
                        ElevatedCard (
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = CardDefaults.elevatedCardColors(
                                containerColor = if (isSystemInDarkTheme()) buttonDark else buttonLight,
                                contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
                            ),
                            shape = RoundedCornerShape(10.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 10.dp
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text("Name: ${studentData?.studentName}", fontSize = 12.sp, fontFamily = Lexend)
                                AddHeight(6.dp)
                                Text("Gender: ${studentData?.gender}", fontSize = 12.sp, fontFamily = Lexend)
                                AddHeight(6.dp)
                                Text("Personal Email: ${studentData?.personalEmail}", fontSize = 12.sp, fontFamily = Lexend)
                                AddHeight(6.dp)
                                Text("DOB: ${studentData?.dob}", fontSize = 12.sp, fontFamily = Lexend)
                                AddHeight(6.dp)
                                Text("Mobile: ${studentData?.phone}", fontSize = 12.sp, fontFamily = Lexend)
                                AddHeight(6.dp)
                                Text("Blood-Group: ${studentData?.bloodGroup}", fontSize = 12.sp, fontFamily = Lexend)
                            }
                        }
                        AddHeight(40.dp)
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text("Academic Calendar", fontFamily = Lexend, fontSize = 20.sp)
                        }
                        AddHeight(20.dp)
                        ElevatedCard (
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = CardDefaults.elevatedCardColors(
                                containerColor = if (isSystemInDarkTheme()) buttonDark else buttonLight,
                                contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
                            ),
                            shape = RoundedCornerShape(10.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 10.dp
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text("Course Registration: 02/06/2025-05/06/2025", fontSize = 12.sp, fontFamily = Lexend)
                                AddHeight(6.dp)
                                Text("Online Feedback: After the Result is Announced", fontSize = 12.sp, fontFamily = Lexend)
                                AddHeight(6.dp)
                                Text("Classes: 10/06/2025-20/07/2025", fontSize = 12.sp, fontFamily = Lexend)
                                AddHeight(6.dp)
                                Text("Online Withdraw Request: 06/06/2025-27/07/2025", fontSize = 12.sp, fontFamily = Lexend)
                                AddHeight(6.dp)
                                Text("Online Retake Request: After Exams", fontSize = 12.sp, fontFamily = Lexend)
                            }
                        }
                        AddHeight(50.dp)
                    }
                }
            }
        }
    }
}