package com.example.gradely.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gradely.ui.theme.Lexend
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.example.gradely.ui.theme.button

@Composable
fun AddWidth(space: Dp) {
    Spacer(modifier = Modifier.width(space))
}

@Composable
fun AddHeight(space: Dp) {
    Spacer(modifier = Modifier.height(space))
}

@Composable
fun Input(
    label : String,
    value : String,
    onValueChange: (String) -> Unit,
    trailingIcon: (@Composable () -> Unit)? = null,
    color: Color,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier
            .fillMaxWidth(fraction = 0.9f),
        label = {
            Text(
                label,
                fontFamily = Lexend
                )
        },
        value = value,
        onValueChange = onValueChange,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = color,
            unfocusedContainerColor = color,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            disabledLabelColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            focusedLabelColor = Color.Gray
        ),
        shape = RoundedCornerShape(10.dp),
        textStyle = TextStyle(
            fontSize = 15.sp,
            fontFamily = Lexend
        )
    )
}

@Composable
fun SideBarItem(
    icon: ImageVector,
    text: String,
    count: Int? = null,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val textColor = if (isSelected) button else if (isSystemInDarkTheme()) Color.White else Color.Black

    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(15.dp)
            )
            AddWidth(5.dp)
            Text(text = text, color = textColor, fontFamily = Lexend, fontSize = 12.sp)
        }
        count?.let {
            Text(text = it.toString(), color = textColor)
        }
    }
    AddHeight(10.dp)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appbar(title: String, openDrawer: () -> Unit, onLogout: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(text = title, style = TextStyle(color = Color.White, fontSize = 20.sp), fontFamily = Lexend)
        },
        navigationIcon = {
            IconButton(onClick = {
                openDrawer()
            }) {
                Icon(Icons.Default.Menu, contentDescription = "menu button", tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "more options", tint = Color.White)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Logout", fontFamily = Lexend) },
                    onClick = {
                        expanded = false
                        onLogout()
                    }
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = button,
            scrolledContainerColor = button
        ),
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun TextModify(bold: String, normal: String, space: Int = 6) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(bold)
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                append(normal)
            }
        },
        fontSize = 12.sp,
        fontFamily = Lexend
    )
    AddHeight(space.dp)
}

fun getCurrentSemesterInfo(): Pair<String, String> {
    val now = java.time.LocalDate.now()
    val month = now.month
    val year = now.year

    return when (month) {
        java.time.Month.JUNE -> "Summer $year" to "2"
        java.time.Month.JANUARY -> "Spring $year" to "6"
        java.time.Month.AUGUST -> "Fall $year" to "6"
        else -> "Unknown Semester" to "0" // fallback for other months
    }
}
