package com.example.gradely.view

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HowToVote
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gradely.ui.theme.Lexend
import com.example.gradely.ui.theme.button
import com.example.gradely.viewmodel.navigation.Screens
import com.example.gradely.viewmodel.viewmodels.StudentRegistrationsViewModel
import com.example.gradely.viewmodel.viewmodels.StudentTokenViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.example.gradely.ui.theme.buttonDark
import com.example.gradely.ui.theme.buttonLight


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentRegistration(
    navController: NavController,
    studentTokenViewModel: StudentTokenViewModel = hiltViewModel(),
    studentRegistrationsViewModel: StudentRegistrationsViewModel = hiltViewModel()
) {
    val studentData = studentTokenViewModel.studentData.collectAsState().value
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val scope = rememberCoroutineScope()
    val courses by studentRegistrationsViewModel.registrationResult.collectAsState()
    val (semester, courseLimit) = getCurrentSemesterInfo()
    val color = if (isSystemInDarkTheme()) buttonDark else buttonLight
    var registeredCredits by remember { mutableIntStateOf(0) }

    LaunchedEffect(studentData?.studentId) {
        studentData?.let {
            studentRegistrationsViewModel.getRegistration(
                studentId = it.studentId,
                token = "Bearer ${it.token}"
            )
        }
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
                        isSelected = false,
                        onClick = {
                            navController.navigate(Screens.StudentHome.route)
                        }
                    )
                    AddHeight(8.dp)
                    SideBarItem(
                        icon = Icons.Default.HowToVote,
                        text = "Course Registration",
                        isSelected = true,
                        onClick = {}
                    )
                    AddHeight(8.dp)
                    SideBarItem(
                        icon = Icons.Default.SupervisorAccount,
                        text = "Attendance",
                        isSelected = false,
                        onClick = {

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

                val (bar, scroll) = createRefs()

                ElevatedCard(
                    modifier = Modifier.constrainAs(bar) {
                        top.linkTo(parent.top, margin = 30.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.percent(0.9f)
                    },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(
                        10.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(15.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        TextModify("Program: ", "${studentData?.degree}")
                        TextModify("Credits Attempted: ", "${studentData?.creditsAttempted}")
                        TextModify("Warning Count: ", "${studentData?.warningCount}")
                        TextModify("Name: ", "${studentData?.studentName}")
                        TextModify("Batch: ", "Fall ${studentData?.batch}")
                        TextModify("Credits Earned: ", "${studentData?.creditsEarned}")
                        TextModify("Semester: ", semester)
                        TextModify("Registered Credits: ", registeredCredits.toString())
                        TextModify("Status: ", "${studentData?.status}")
                        TextModify("CGPA: ", "${studentData?.cgpa}")
                        TextModify("Course Limit for Semester: ", courseLimit)
                    }
                }

                LazyColumn (
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .constrainAs(scroll) {
                            top.linkTo(bar.bottom, margin = 30.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.percent(0.9f)
                            bottom.linkTo(parent.bottom, margin = 30.dp)
                            height = Dimension.fillToConstraints
                        }
                ) {
                    item {
                        AddHeight(30.dp)
                    }

                    Log.d("Registration Page Check", courses.toString())

                    items(courses?.size ?: 0) { index ->

                        val course = courses?.get(index)
                        val isExpanded1 = remember { mutableStateOf(false) }
                        val isExpanded2 = remember { mutableStateOf(false) }
                        val section = remember { mutableStateOf("") }
                        val register = remember { mutableStateOf(false) }
                        val teacher = remember { mutableStateOf("") }
                        val selectedTeacher = remember { mutableStateOf("") }
                        val filteredSections = remember(selectedTeacher.value, course) {
                            derivedStateOf {
                                course?.available?.filter { it.teacherId == selectedTeacher.value } ?: emptyList()
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "${index + 1}",
                                fontFamily = Lexend,
                                fontSize = 12.sp,
                            )
                            Box (
                                modifier = Modifier.width(150.dp).height(50.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = course?.courseName ?: "N/A",
                                    fontFamily = Lexend,
                                    fontSize = 12.sp,
                                    maxLines = 4,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                            Text(
                                course?.status ?: "N/A",
                                fontFamily = Lexend,
                                fontSize = 12.sp,
                            )
                            Box(
                                modifier = Modifier
                                    .width(110.dp)
                                    .height(45.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                ExposedDropdownMenuBox(
                                    expanded = isExpanded1.value,
                                    onExpandedChange = { isExpanded1.value = !isExpanded1.value }
                                ) {
                                    TextField(
                                        value = teacher.value,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded1.value)
                                        },
                                        colors = ExposedDropdownMenuDefaults.textFieldColors(
                                            focusedContainerColor = color,
                                            unfocusedContainerColor = color,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            disabledIndicatorColor = Color.Transparent,
                                            disabledLabelColor = Color.Gray,
                                            unfocusedLabelColor = Color.Gray,
                                            focusedLabelColor = Color.Gray
                                        ),
                                        modifier = Modifier.menuAnchor(),
                                        textStyle = TextStyle(fontFamily = Lexend, fontSize = 10.sp)
                                    )

                                    ExposedDropdownMenu(
                                        expanded = isExpanded1.value,
                                        onDismissRequest = { isExpanded1.value = false }
                                    ) {
                                        course?.available
                                            ?.distinctBy { it.teacherId }
                                            ?.forEach { sec ->
                                            DropdownMenuItem(
                                                text = { Text(sec.teacherName, fontSize = 10.sp, fontFamily = Lexend) },
                                                onClick = {
                                                    teacher.value = sec.teacherName
                                                    selectedTeacher.value = sec.teacherId
                                                    section.value = ""
                                                    isExpanded1.value = false
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .width(110.dp)
                                    .height(45.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                ExposedDropdownMenuBox(
                                    expanded = isExpanded2.value,
                                    onExpandedChange = { isExpanded2.value = !isExpanded2.value }
                                ) {
                                    TextField(
                                        value = section.value,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded2.value)
                                        },
                                        colors = ExposedDropdownMenuDefaults.textFieldColors(
                                            focusedContainerColor = color,
                                            unfocusedContainerColor = color,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            disabledIndicatorColor = Color.Transparent,
                                            disabledLabelColor = Color.Gray,
                                            unfocusedLabelColor = Color.Gray,
                                            focusedLabelColor = Color.Gray
                                        ),
                                        modifier = Modifier.menuAnchor(),
                                        textStyle = TextStyle(fontFamily = Lexend, fontSize = 10.sp)
                                    )

                                    ExposedDropdownMenu(
                                        expanded = isExpanded2.value,
                                        onDismissRequest = { isExpanded2.value = false }
                                    ) {
                                        filteredSections.value.forEach { sec ->
                                            DropdownMenuItem(
                                                text = { Text(sec.sectionName, fontSize = 10.sp, fontFamily = Lexend) },
                                                onClick = {
                                                    section.value = sec.sectionName
                                                    isExpanded2.value = false
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                            ElevatedButton(
                                onClick = {
                                    register.value = !register.value
                                    if (!register.value) {
                                        course?.creditHours?.let { registeredCredits -= it }
                                    } else {
                                        course?.creditHours?.let { registeredCredits += it }
                                    }
                                },
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = button,
                                    contentColor = Color.White
                                ),
                                elevation = ButtonDefaults.buttonElevation(10.dp)
                            ) {
                                Text(
                                    if (!register.value) "Register" else "Drop",
                                    fontSize = 10.sp,
                                    fontFamily = Lexend
                                )
                            }
                        }
                        AddHeight(10.dp)
                        HorizontalDivider(color = Color.Gray)
                        AddHeight(10.dp)
                    }
                }
            }
        }
    }
}