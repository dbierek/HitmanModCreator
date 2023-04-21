package com.dannybierek.tools.hmc

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


@Composable
@Preview
fun App() {

    val list = listOf(
        UI.HEADER_TEXT,
        UI.OPEN_MISSION_BUTTON,
        UI.DROPDOWN,
        UI.MISSION_THUMBNAIL,
        UI.THUMBNAIL_DROPDOWN,
        UI.CREATE_MISSION_BUTTON,
        "Open a mission by clicking the Open Mission button.",
        "Then click the Create Mod button."
    )
    val primaryColor = 0xff5abbe2
    val secondaryColor = 0xff3f8c5a
    val imageModifier = Modifier
        .height(240.dp)
        .fillMaxWidth()
        .clip(
            RoundedCornerShape(12.dp)
        )


    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = list, itemContent = { item ->
            when (item) {
                UI.HEADER_TEXT -> {
                    Text(text = (item as UI).text, style = TextStyle(fontSize = 20.sp))
                }

                UI.CREATE_MISSION_BUTTON -> {
                    var text by remember { mutableStateOf("Create Mission") }
                    MaterialTheme(
                        lightColors().copy(
                            primary = Color(primaryColor),
                            secondary = Color(secondaryColor)
                        )
                    ) {
                        StartGenerateMissionApplication()

                        Button(onClick = {
                            text = "Mission Created"

                        }) {
                            Text(text)
                        }
                    }
                }

                UI.OPEN_MISSION_BUTTON -> {
                    var text by remember { mutableStateOf("Open Mission") }
                    MaterialTheme(
                        lightColors().copy(
                            primary = Color(primaryColor),
                            secondary = Color(secondaryColor)
                        )
                    ) {
                        Button(onClick = { text = "Mission Opened" }) {
                            Text(text = text, style = TextStyle(fontSize = 20.sp))
                        }
                    }
                }

                UI.DROPDOWN -> {
                    var expanded by remember { mutableStateOf(false) }
                    val items = listOf("TEMP", "AIRG", "TBLU", "PRIM", "ASET", "BORG")
                    val disabledValue = "BORG"
                    var selectedIndex by remember { mutableStateOf(0) }
                    Box(modifier = Modifier.fillMaxSize(.5F).wrapContentSize(Alignment.TopStart)) {
                        Text(
                            items[selectedIndex],
                            modifier = Modifier.fillMaxWidth().clickable(onClick = { expanded = true }).background(
                                Color(primaryColor)
                            )
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.fillMaxWidth().background(
                                Color(secondaryColor)
                            )
                        ) {
                            items.forEachIndexed { index, s ->
                                DropdownMenuItem(onClick = {
                                    selectedIndex = index
                                    expanded = false
                                }) {
                                    val disabledText = if (s == disabledValue) {
                                        " (Disabled)"
                                    } else {
                                        ""
                                    }
                                    Text(text = s + disabledText)
                                }
                            }
                        }
                    }
                }

//                UI.THUMBNAIL_DROPDOWN -> {
//                    var expanded by remember { mutableStateOf(false) }
//                    val missionsDirectory = File("images")
//                    val items = missionsDirectory.list()
//                    var selectedIndex by remember { mutableStateOf(0) }
//                    Box(modifier = Modifier.fillMaxSize(.5F).wrapContentSize(Alignment.TopStart)) {
//                        Image(
//                            painter = painterResource(items[selectedIndex]),
//                            contentDescription = "image",
//                            imageModifier,
//                            contentScale = ContentScale.Fit
//                        )
//                        DropdownMenu(
//                            expanded = expanded,
//                            onDismissRequest = { expanded = false },
//                            modifier = Modifier.fillMaxWidth().background(
//                                Color(secondaryColor)
//                            )
//                        ) {
//                            items.forEachIndexed { index, s ->
//                                DropdownMenuItem(onClick = {
//                                    selectedIndex = index
//                                    expanded = false
//                                }) {
//                                    Image(
//                                        painter = painterResource(items[selectedIndex]),
//                                        contentDescription = "image",
//                                        imageModifier,
//                                        contentScale = ContentScale.Fit
//                                    )
//                                }
//                            }
//                        }
//                    }
//                }

                UI.MISSION_THUMBNAIL -> {
                    val fileName = "images/newyork.jpg"
                    Image(
                        painter = painterResource(fileName),
                        contentDescription = "image",
                        imageModifier,
                        contentScale = ContentScale.Fit
                    )
                }

                else -> {
                    var text: String
                    if (item is String) {
                        text = item
                    } else {
                        text = item.toString()
                    }
                    Text(text = text, style = TextStyle(fontSize = 20.sp))
                }
            }
        })
    }
    Canvas2D()

}
fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
