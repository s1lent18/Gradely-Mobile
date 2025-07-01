package com.example.gradely.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HowToVote
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gradely.model.models.CourseAttendance
import com.example.gradely.ui.theme.Lexend
import com.example.gradely.ui.theme.button
import com.example.gradely.ui.theme.buttonDark
import com.example.gradely.ui.theme.buttonLight
import com.example.gradely.viewmodel.navigation.Screens
import com.example.gradely.viewmodel.viewmodels.StudentAttendanceViewModel
import com.example.gradely.viewmodel.viewmodels.StudentTokenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CourseSelectionAttendance(course: String, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = button,
            contentColor = Color.White
        ),
        modifier = Modifier
            .width(70.dp)
            .height(40.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(course, fontWeight = FontWeight.Bold, fontFamily = Lexend, fontSize = 12.sp)
        }
    }
}

@Composable
fun ProgressBar(progress: Float) {
    val animatedProgress by animateFloatAsState(progress)
    val gradient = Brush.horizontalGradient(colors = listOf(Color(0xFF00c6FF), Color(0xFF0072FF)))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(16.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color.LightGray)
    ) {
        Box(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(animatedProgress)
            .background(gradient)
        )
    }
}

@Composable
fun Progress(limit: Float) {
    var progress by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(Unit) {
        if (progress < limit) {
            delay(5)
            progress += 0.002f
        }
    }
    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Attendance Percentage ${(limit / 1f) * 100}")
        ProgressBar(progress = progress)
        AddHeight(10.dp)
    }
}

@Composable
fun AttendanceCard(courseAttendance: CourseAttendance) {

    val presents = courseAttendance.attendances.count { it.status == "Present" }
    val total = courseAttendance.attendances.count { it.status != "Present" }

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) buttonDark else buttonLight
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text("${courseAttendance.courseCode} - ${courseAttendance.courseName} (${courseAttendance.sectionName})", fontFamily = Lexend, fontSize = 10.sp)
            AddHeight(10.dp)
            Progress(limit = (presents / total).toFloat())
        }
    }
}

@Composable
fun Attendances(lectureNo : String = "No", date: String = "Date", duration: String = "Duration", presence: String = "Presence") {
    Row (
        modifier = Modifier.clip(RoundedCornerShape(4.dp)) .fillMaxWidth().height(50.dp).background(button),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.fillMaxHeight().weight(0.15f).padding(5.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(lectureNo, fontFamily = Lexend, fontSize = 10.sp)
        }

        Box(
            modifier = Modifier.fillMaxHeight().weight(0.25f),
            contentAlignment = Alignment.Center
        ) {
            Text(date, fontFamily = Lexend, fontSize = 10.sp)
        }

        Box(
            modifier = Modifier.fillMaxHeight().weight(0.25f),
            contentAlignment = Alignment.Center
        ) {
            Text(duration, fontFamily = Lexend, fontSize = 10.sp)
        }

        Box(
            modifier = Modifier.fillMaxHeight().weight(0.2f),
            contentAlignment = Alignment.Center
        ) {
            Text(presence, fontFamily = Lexend, fontSize = 10.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentAttendance(
    navController : NavController,
    studentTokenViewModel: StudentTokenViewModel = hiltViewModel(),
    studentAttendanceViewModel: StudentAttendanceViewModel = hiltViewModel()
) {
    val studentData = studentTokenViewModel.studentData.collectAsState().value
    val isExpanded = remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val scope = rememberCoroutineScope()
    val color = if (isSystemInDarkTheme()) buttonDark else buttonLight
    val attendance = studentAttendanceViewModel.attendanceResult.collectAsState().value
    val semester = remember { mutableStateListOf<CourseAttendance>() }
    val selectedCourse = remember { mutableStateOf<CourseAttendance?>(null) }

    LaunchedEffect(studentData?.studentId) {
        studentData?.let {
            studentAttendanceViewModel.getAttendance(
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
                Appbar("Marks", openDrawer = {
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
                val (dropDown, lazyRow, tell, lazyColumn) = createRefs()

                if (attendance == null) {
                    Box (
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    val selectedSemester = remember { mutableStateOf(attendance.attendance[0].name) }
                    Row (
                        modifier = Modifier.constrainAs(dropDown) {
                            top.linkTo(parent.top, margin = 30.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ExposedDropdownMenuBox(
                            expanded = isExpanded.value,
                            onExpandedChange = { isExpanded.value = !isExpanded.value }
                        ) {
                            TextField(
                                value = selectedSemester.value,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded.value)
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
                                modifier = Modifier
                                    .menuAnchor(
                                        type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                                        enabled = true
                                    )
                                    .fillMaxWidth(fraction = 0.4f)
                                    .height(50.dp),
                                textStyle = TextStyle(fontFamily = Lexend, fontSize = 10.sp, textAlign = TextAlign.Center),
                                shape = RoundedCornerShape(10.dp)
                            )

                            ExposedDropdownMenu(
                                expanded = isExpanded.value,
                                onDismissRequest = { isExpanded.value = false }
                            ) {
                                attendance.attendance.forEach { it ->
                                    DropdownMenuItem(
                                        text = { Text(it.name, fontSize = 10.sp, fontFamily = Lexend) },
                                        onClick = {
                                            selectedSemester.value = it.name
                                            semester.clear()
                                            semester.addAll(it.courseAttendances)
                                            isExpanded.value = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    LazyRow (
                        modifier = Modifier.constrainAs(lazyRow) {
                            top.linkTo(dropDown.bottom, margin = 30.dp)
                            width = Dimension.fillToConstraints
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 8.dp),
                    ) {
                        items (semester) { course ->
                            CourseSelectionAttendance(course.courseCode, onClick = { selectedCourse.value = course })
                            AddWidth(14.dp)
                        }
                    }

                    LazyColumn (
                        modifier = Modifier.constrainAs(lazyColumn) {
                            top.linkTo(lazyRow.bottom, margin = 20.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.percent(0.9f)
                            bottom.linkTo(parent.bottom, margin = 30.dp)
                            height = Dimension.fillToConstraints
                        }
                    ) {
                        item {
                            if (selectedCourse.value != null) {
                                AttendanceCard(selectedCourse.value!!)
                            }
                        }

                        item {
                            AddHeight(20.dp)
                        }

                        item {
                            Attendances()
                            AddHeight(20.dp)
                        }

                        if (selectedCourse.value != null) {
                            items(selectedCourse.value!!.attendances) { attendance ->
                                Attendances(
                                    lectureNo = "",
                                    date = attendance.date,
                                    duration = "1",
                                    presence = if (attendance.status == "Present") "P" else if (attendance.status == "Absent") "A" else "L"
                                )
                                AddHeight(20.dp)
                            }
                        }
                    }
                }
            }
        }
    }
}