package com.example.gradely.view

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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
import androidx.compose.runtime.setValue
import com.example.gradely.model.models.CourseXX

@Composable
fun CourseSelection(text: String, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = button,
            contentColor = Color.White
        ),
        modifier = Modifier.width(70.dp).height(40.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text, fontWeight = FontWeight.Bold, fontFamily = Lexend, fontSize = 12.sp)
        }
    }
}

@Preview
@Composable
fun MarksCard() {
    ElevatedCard (
        modifier = Modifier.fillMaxWidth().height(100.dp),
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
            Text("39", fontFamily = Lexend, fontWeight = FontWeight.Bold)
            VerticalDivider(
                modifier = Modifier.fillMaxHeight(fraction = 0.8f),
                color = Color.Black
            )
            Text("50", fontFamily = Lexend, fontWeight = FontWeight.Bold)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentMarks(
    navController : NavController,
    studentTokenViewModel : StudentTokenViewModel = hiltViewModel(),
    studentMarksViewModel: StudentMarksViewModel = hiltViewModel()
) {

    val semesters by studentMarksViewModel.semestersResult.collectAsState()
    val studentData = studentTokenViewModel.studentData.collectAsState().value
    val semester = remember { mutableStateOf("") }
    val isExpanded = remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val scope = rememberCoroutineScope()
    val color = if (isSystemInDarkTheme()) buttonDark else buttonLight
    val courses = listOf(semesters?.semesters[semesters!!.semesters.size - 1]?.courses ?: "")

    LaunchedEffect(studentData?.studentId) {
        studentData?.let {
            studentMarksViewModel.getSemesters(
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
                            value = semester.value,
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
                            modifier = Modifier.menuAnchor().fillMaxWidth(fraction = 0.6f).height(50.dp),
                            textStyle = TextStyle(fontFamily = Lexend, fontSize = 10.sp, textAlign = TextAlign.Center),
                            shape = RoundedCornerShape(10.dp)
                        )

                        ExposedDropdownMenu(
                            expanded = isExpanded.value,
                            onDismissRequest = { isExpanded.value = false }
                        ) {
                            listOf(
                                "Fall 2022",
                                "Spring 2023",
                                "Summer 2023",
                                "Fall 2023",
                                "Spring 2024"
                            ).forEach { sec ->
                                DropdownMenuItem(
                                    text = { Text(sec, fontSize = 10.sp, fontFamily = Lexend) },
                                    onClick = {
                                        semester.value = sec
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
                        CourseSelection(course, onClick = {})
                        AddWidth(14.dp)
                    }
                }

                Row (
                    modifier = Modifier.constrainAs(tell) {
                        top.linkTo(lazyRow.bottom, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.percent(0.9f)
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text("Marks Obtained", fontFamily = Lexend, fontWeight = FontWeight.Bold)
                    VerticalDivider(
                        modifier = Modifier.fillMaxHeight(fraction = 0.8f),
                        color = Color.Black
                    )
                    Text("Total Marks", fontFamily = Lexend, fontWeight = FontWeight.Bold)
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

                    }
                }
            }
        }
    }
}