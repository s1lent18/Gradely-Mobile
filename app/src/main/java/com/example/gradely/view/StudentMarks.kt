package com.example.gradely.view

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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
import com.example.gradely.ui.theme.Lexend
import com.example.gradely.ui.theme.button
import com.example.gradely.ui.theme.buttonDark
import com.example.gradely.ui.theme.buttonLight
import com.example.gradely.viewmodel.navigation.Screens
import com.example.gradely.viewmodel.viewmodels.StudentMarksViewModel
import com.example.gradely.viewmodel.viewmodels.StudentTokenViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.example.gradely.model.dataRequests.StudentAllResultRequest
import com.example.gradely.model.models.Course

@Composable
fun CourseSelection(course: Course, onClick: () -> Unit) {
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
            Text(course.details?.courseCode ?: "N/A", fontWeight = FontWeight.Bold, fontFamily = Lexend, fontSize = 12.sp)
        }
    }
}

@Composable
fun MarksCard(score: String, total: String, onClick: () -> Unit) {
    ElevatedCard (
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) buttonDark else buttonLight,
            contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(score, fontFamily = Lexend, fontWeight = FontWeight.Bold)
            VerticalDivider(
                modifier = Modifier.fillMaxHeight(fraction = 0.8f),
                color = Color.Black
            )
            Text(total, fontFamily = Lexend, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun MarksListCard(score: List<String>, total: List<String>) {
    ElevatedCard (
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) buttonDark else buttonLight,
            contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column (
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                score.forEach {
                    Text(it, fontFamily = Lexend, fontWeight = FontWeight.Bold)
                }
            }
            VerticalDivider(
                modifier = Modifier.fillMaxHeight(fraction = 0.8f),
                color = Color.Black
            )
            Column (
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                total.forEach {
                    Text(it, fontFamily = Lexend, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@SuppressLint("ConfigurationScreenWidthHeight")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentMarks(
    navController : NavController,
    studentTokenViewModel : StudentTokenViewModel = hiltViewModel(),
    studentMarksViewModel: StudentMarksViewModel = hiltViewModel()
) {
    val allResult by studentMarksViewModel.allResult.collectAsState()
    val studentResult by studentMarksViewModel.semestersResult.collectAsState()
    val studentData = studentTokenViewModel.studentData.collectAsState().value
    val isExpanded = remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val scope = rememberCoroutineScope()
    val color = if (isSystemInDarkTheme()) buttonDark else buttonLight
    val courses = studentResult?.semesters?.lastOrNull()?.courses ?: emptyList()
    val semesters = studentResult?.semesters ?: emptyList()

    val selectedCourse = remember { mutableStateOf<Course?>(null) }

    LaunchedEffect(studentData?.studentId) {
        studentData?.let {
            studentMarksViewModel.getSemesters(
                studentId = it.studentId,
                token = "Bearer ${it.token}"
            )
        }
    }

    LaunchedEffect(studentData?.studentId, selectedCourse.value) {
        studentData?.let { student ->
            val section = selectedCourse.value?.section
            val courseId = selectedCourse.value?.courseId

            if (!section.isNullOrBlank() && !courseId.isNullOrBlank()) {
                studentMarksViewModel.getAllResults(
                    token = "Bearer ${student.token}",
                    request = StudentAllResultRequest(
                        Id = section,
                        courseId = courseId
                    )
                )
            }
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

                if (studentResult == null) {
                    Box (
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    val selectedSemester = remember { mutableStateOf(semesters[semesters.size - 1]) }
                    selectedCourse.value = selectedSemester.value.courses[0]
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
                                value = selectedSemester.value.name,
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
                                studentResult!!.semesters.forEach { it ->
                                    DropdownMenuItem(
                                        text = { Text(it.name, fontSize = 10.sp, fontFamily = Lexend) },
                                        onClick = {
                                            selectedSemester.value = it
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
                        items (courses) { course ->
                            CourseSelection(course, onClick = { selectedCourse.value = course })
                            AddWidth(14.dp)
                        }
                    }

                    Row (
                        modifier = Modifier
                            .constrainAs(tell) {
                                top.linkTo(lazyRow.bottom, margin = 20.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .fillMaxWidth()
                            .height(50.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ElevatedCard (
                            modifier = Modifier
                                .fillMaxWidth(fraction = 0.8f),
                            shape = RoundedCornerShape(6.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSystemInDarkTheme()) buttonDark else buttonLight,
                                contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
                            ),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Row (
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text("Marks Obtained", fontFamily = Lexend, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                VerticalDivider(
                                    modifier = Modifier.fillMaxHeight(fraction = 0.8f),
                                    color = Color.Black
                                )
                                Text("Total Marks", fontFamily = Lexend, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                        }
                    }

                    LazyColumn (
                        modifier = Modifier.constrainAs(lazyColumn) {
                            top.linkTo(tell.bottom, margin = 20.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.percent(0.9f)
                            bottom.linkTo(parent.bottom, margin = 30.dp)
                            height = Dimension.fillToConstraints
                        }
                    ) {
                        item {

                            selectedCourse.value?.let {
                                if (it.details?.assignments?.isNotEmpty() ?: false) {
                                    Text("Assignments", fontSize = 14.sp, fontFamily = Lexend, fontWeight = FontWeight.Bold)
                                    AddHeight(5.dp)
                                    val scores = mutableListOf<String>()
                                    val totals = mutableListOf<String>()
                                    it.details.assignments.forEach { assignment ->
                                        scores.add(assignment.assignmentScore)
                                        totals.add(assignment.assignmentTotal)
                                    }
                                    MarksListCard(score = scores, total = totals)
                                }
                            }

                            AddHeight(30.dp)

                            selectedCourse.value?.let {
                                if (it.details?.quizzes?.isNotEmpty() ?: false) {
                                    Text("Quiz", fontSize = 14.sp, fontFamily = Lexend, fontWeight = FontWeight.Bold)
                                    AddHeight(5.dp)
                                    val scores = mutableListOf<String>()
                                    val totals = mutableListOf<String>()
                                    it.details.quizzes.forEach { assignment ->
                                        scores.add(assignment.quizScore)
                                        totals.add(assignment.quizTotal)
                                    }
                                    MarksListCard(score = scores, total = totals)
                                }
                            }

                            AddHeight(30.dp)

                            selectedCourse.value?.let {
                                if (it.details?.mid1?.examScore != "?") {
                                    Text("Mid-1", fontSize = 14.sp, fontFamily = Lexend, fontWeight = FontWeight.Bold)
                                    AddHeight(5.dp)
                                    if (it.details != null) {
                                        MarksCard(
                                            (
                                                    (it.details.mid1.examScore.toDouble()
                                                        .div(
                                                            it.details.mid1.examTotal.toDouble()
                                                        )).times(
                                                            it.details.mid1.weightage.toDouble()
                                                        )).toString(),
                                            it.details.mid1.weightage,
                                            onClick = {}
                                        )
                                    }
                                }
                            }

                            AddHeight(30.dp)

                            selectedCourse.value?.let {
                                if (it.details?.mid2?.examScore != "?") {
                                    Text("Mid-2", fontSize = 14.sp, fontFamily = Lexend, fontWeight = FontWeight.Bold)
                                    AddHeight(5.dp)
                                    if (it.details != null) {
                                        MarksCard(
                                            (
                                                    (it.details.mid2.examScore.toDouble() / it.details.mid2.examTotal.toDouble())
                                                            * it.details.mid2.weightage.toDouble()).toString(),
                                            it.details.mid2.weightage,
                                            onClick = {}
                                        )
                                    }
                                }
                            }

                            AddHeight(30.dp)

                            selectedCourse.value?.let {
                                if (it.details?.classParticipationScore != "?") {
                                    Text("Class Participation", fontSize = 14.sp, fontFamily = Lexend, fontWeight = FontWeight.Bold)
                                    AddHeight(5.dp)
                                    MarksCard(it.details?.classParticipationScore ?: "",
                                        it.details?.classParticipationTotal ?: "", onClick = {})
                                }
                            }

                            AddHeight(30.dp)

                            selectedCourse.value?.let {
                                if (it.details?.projectScore != "?") {
                                    Text("Project", fontSize = 14.sp, fontFamily = Lexend, fontWeight = FontWeight.Bold)
                                    AddHeight(5.dp)
                                    MarksCard(it.details?.projectScore ?: "",
                                        it.details?.projectTotal ?: "", onClick = {})
                                }
                            }

                            AddHeight(30.dp)

                            selectedCourse.value?.let {
                                if (it.details?.finalExam?.examScore != "?") {
                                    Text("Final Exams", fontSize = 14.sp, fontFamily = Lexend, fontWeight = FontWeight.Bold)
                                    AddHeight(5.dp)
                                    if (it.details != null) {
                                        MarksCard(
                                            (
                                                    (it.details.finalExam.examScore.toDouble() / it.details.finalExam.examTotal.toDouble())
                                                            * it.details.finalExam.weightage.toDouble()).toString(),
                                            it.details.finalExam.weightage,
                                            onClick = {}
                                        )
                                    }
                                }
                            }

                            AddHeight(30.dp)
                            selectedCourse.value?.let {
                                if (it.savePoints.isNotEmpty()) {
                                    Text("Progress Throughout the course", fontSize = 14.sp, fontFamily = Lexend, fontWeight = FontWeight.Bold)
                                    val yPoints = it.savePoints
                                    Log.d("savePoints Check", "$yPoints")
                                    val pointsData: List<Point> = yPoints.mapIndexed { index, y ->
                                        Point((index + 1).toFloat(), y.toFloat())
                                    }
                                    Log.d("savePoints Check", "$pointsData")
                                    val configuration = LocalConfiguration.current
                                    val screenWidthDp = configuration.screenWidthDp.dp
                                    val numberOfSteps = (pointsData.size - 1).coerceAtLeast(1) // avoid divide-by-zero

                                    val stepSize = with(LocalDensity.current) {
                                        (screenWidthDp / numberOfSteps)
                                    }
                                    val xAxisData = AxisData.Builder()
                                        .axisStepSize(stepSize)
                                        .backgroundColor(Color.Transparent)
                                        .steps(pointsData.size - 1)
                                        .labelData { i -> i.toString() }
                                        .labelAndAxisLinePadding(15.dp)
                                        .build()

                                    val steps = 10
                                    val yAxisData = AxisData.Builder()
                                        .steps(steps)
                                        .backgroundColor(Color.Transparent)
                                        .labelAndAxisLinePadding(20.dp)
                                        .labelData { i ->
                                            val yScale = 100 / steps
                                            (i * yScale).toString()
                                        }.build()
                                    val lineChartData = LineChartData(
                                        linePlotData = LinePlotData(
                                            lines = listOf(
                                                Line(
                                                    dataPoints = pointsData,
                                                    LineStyle(),
                                                    IntersectionPoint(),
                                                    SelectionHighlightPoint(),
                                                    ShadowUnderLine(),
                                                    SelectionHighlightPopUp()
                                                )
                                            ),
                                        ),
                                        xAxisData = xAxisData,
                                        yAxisData = yAxisData,
                                        gridLines = GridLines(),
                                        backgroundColor = Color.Transparent
                                    )
                                    LineChart(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(300.dp),
                                        lineChartData = lineChartData
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}