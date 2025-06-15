package com.example.gradely.view

import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
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
import com.example.gradely.viewmodel.viewmodels.StudentTokenViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentRegistration(
    navController: NavController,
    studentTokenViewModel: StudentTokenViewModel = hiltViewModel()
) {
    val studentData = studentTokenViewModel.studentData.collectAsState().value
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val scope = rememberCoroutineScope()
    val expandedStates = remember { mutableStateListOf<Boolean>().apply { repeat(5) { add(false) } } }
    val sections = remember { mutableStateListOf<String>().apply { repeat(5) { add("") } } }
    val registration = remember { mutableStateListOf<Boolean>().apply { repeat(5) { add(false) } } }
    //var section by remember { mutableStateOf("") }
    val (semester, courseLimit) = getCurrentSemesterInfo()

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
                Appbar("Course Registration") {
                    scope.launch { drawerState.open() }
                }
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
                        TextModify("Registered Credits: ", "0")
                        TextModify("Status: ", "${studentData?.creditsEarned}")
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

                    items(5) { index ->

                        val isExpanded = expandedStates[index]
                        var section = sections[index]
                        var register = registration[index]

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
                                //modifier = Modifier.weight(0.1f)
                            )
                            Box (
                                modifier = Modifier.width(80.dp).height(50.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Deep Learning for Perception",
                                    fontFamily = Lexend,
                                    fontSize = 12.sp,
                                    maxLines = 4,
                                    overflow = TextOverflow.Ellipsis,
                                    //modifier = Modifier.weight(0.3f)
                                )
                            }
                            Text(
                                "Elective",
                                fontFamily = Lexend,
                                fontSize = 12.sp,
                                //modifier = Modifier.weight(0.2f)
                            )
                            Box(
                                modifier = Modifier
                                    //.weight(0.3f)
                                    .width(110.dp)
                                    .height(45.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                ExposedDropdownMenuBox(
                                    expanded = isExpanded,
                                    onExpandedChange = { expandedStates[index] = !isExpanded }
                                ) {
                                    TextField(
                                        value = section,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                                        modifier = Modifier.menuAnchor(),
                                        textStyle = TextStyle(fontFamily = Lexend, fontSize = 10.sp)
                                    )

                                    ExposedDropdownMenu(
                                        expanded = isExpanded,
                                        onDismissRequest = { expandedStates[index] = false }
                                    ) {
                                        listOf("BS(CS)-1A", "BS(CS)-1B", "BS(CS)-1C").forEach { sec ->
                                            DropdownMenuItem(
                                                text = { Text(sec, fontSize = 10.sp, fontFamily = Lexend) },
                                                onClick = {
                                                    sections[index] = sec
                                                    expandedStates[index] = false
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                            ElevatedButton(
                                onClick = {
                                    registration[index] = !register
                                },
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = button,
                                    contentColor = Color.White
                                ),
                                //modifier = Modifier.weight(0.2f),
                                elevation = ButtonDefaults.buttonElevation(10.dp)
                            ) {
                                Text( if(!registration[index]) "Register" else "Drop", fontSize = 10.sp, fontFamily = Lexend)
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