package com.dannybierek.tools.hmc.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dannybierek.tools.hmc.GenerateMissionApplication
import com.dannybierek.tools.hmc.config.GenerateMissionProperties

data class HomeScreen(
    val drawEntities: MutableList<DrawEntity> = mutableListOf<DrawEntity>(),
    var getStatusText: (String) -> String = { "Status: $it" },
    var statusTextElement: HeaderText = HeaderText(getStatusText("text")),
    val primaryColor: Color = Color(0xff5abbe2),
    val secondaryColor: Color = Color(0xff3f8c5a),
    val imageModifier: Modifier = Modifier
    .height(240.dp)
    .fillMaxWidth(0.5F)
    .clip(
        RoundedCornerShape(12.dp)
    ),
    override var renderer: @Composable () -> Unit = @Composable {

        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(items = listOf(
                HeaderText("Hitman Mod Creator"),
                HeaderText("Open a mission by clicking the Open Mission button."),
                HeaderText("Then click the Create Mod button."),
                statusTextElement,
                UiButton("Load Mission", primaryColor, secondaryColor) { statusTextElement.text = getStatusText("Loading Mission") },
                DropDownList(listOf("A", "B"), primaryColor, secondaryColor),
                Image("images/newyork.jpg", imageModifier),
                UiButton("Create Mission", clickHandler = {
                    GenerateMissionApplication().startApplication(
                        arrayOf(),
                        GenerateMissionProperties(
                            sceneFile = "src/main/resources/scenes/outfit_showcase.entity.json",
                            statusTextElement = statusTextElement,
                            drawEntities = drawEntities.map { it.entity }
                                .toMutableList()
                        )
                    ) {
                        statusTextElement.text = "Clicked Generate Button"
                    }
                })
            ), itemContent = { item -> item.renderer() })
        }
    }
) : UiElement {

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

UI.THUMBNAIL_DROPDOWN -> {
    var expanded by remember { mutableStateOf(false) }
    val missionsDirectory = File("images")
    val items = missionsDirectory.list()
    var selectedIndex by remember { mutableStateOf(0) }
    Box(modifier = Modifier.fillMaxSize(.5F).wrapContentSize(Alignment.TopStart)) {
        Image(
            painter = painterResource(items[selectedIndex]),
            contentDescription = "image",
            imageModifier,
            contentScale = ContentScale.Fit
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
                    Image(
                        painter = painterResource(items[selectedIndex]),
                        contentDescription = "image",
                        imageModifier,
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}

*/