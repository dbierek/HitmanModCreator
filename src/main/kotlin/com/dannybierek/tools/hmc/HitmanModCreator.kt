package com.dannybierek.tools.hmc

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.dannybierek.tools.hmc.config.GenerateMissionProperties
import com.dannybierek.tools.hmc.ui.UiButton
import com.dannybierek.tools.hmc.ui.Canvas2D
import com.dannybierek.tools.hmc.ui.DrawEntity
import com.dannybierek.tools.hmc.ui.DropDownList
import com.dannybierek.tools.hmc.ui.HeaderText
import com.dannybierek.tools.hmc.ui.Image
import com.dannybierek.tools.hmc.ui.UiElement
import java.awt.SystemColor.text


@Composable
@Preview
fun App() {
    val drawEntities = mutableListOf<DrawEntity>()
    var statusText: String = ""
    val primaryColor = Color(0xff5abbe2)
    val secondaryColor = Color(0xff3f8c5a)
    val imageModifier = Modifier
        .height(240.dp)
        .fillMaxWidth(0.5F)
        .clip(
            RoundedCornerShape(12.dp)
        )
//        UI.THUMBNAIL_DROPDOWN,
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = listOf(
            HeaderText("Hitman Mod Creator"),
            HeaderText("Open a mission by clicking the Open Mission button."),
            HeaderText("Then click the Create Mod button."),
            HeaderText("Status: $statusText"),
            UiButton("Load Mission", primaryColor, secondaryColor) { statusText = "Loading Mission" },
            DropDownList(listOf("A", "B"), primaryColor, secondaryColor),
            Image("images/newyork.jpg", imageModifier),
            UiButton("Create Mission", clickHandler = {
                GenerateMissionApplication().startApplication(arrayOf(), GenerateMissionProperties(drawEntities.map { it.entity }
                    .toMutableList())
                ) {
                    statusText = "Position of entity: [$it]"
                }
            })
        ), itemContent = { item -> item.renderer() })
    }

    Canvas2D(drawEntities)

}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

/*
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

                UI.DROPDOWN ->

                UI.MISSION_THUMBNAIL -> {

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
 */
