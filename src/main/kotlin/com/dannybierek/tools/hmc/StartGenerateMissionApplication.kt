package com.dannybierek.tools.hmc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.dannybierek.tools.hmc.config.GenerateMissionProperties
import kotlinx.coroutines.launch

@Composable
fun StartGenerateMissionApplication(properties: GenerateMissionProperties) {
    var backgroundColor = Color.DarkGray
    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
            .fillMaxSize()
            .background(color = backgroundColor)
    )
    var text: String = "Position of entity: "
    Text(text = text, style = TextStyle(fontSize = 20.sp))

    // Returns a scope that's cancelled when F is removed from composition
    val coroutineScope = rememberCoroutineScope()

    var (generateMissionApplication, setGenerateMissionApplication) = remember {
        mutableStateOf<GenerateMissionApplication?>(
            null
        )
    }

    val getGenerateMissionApplicationOnClick: () -> Unit = {
        coroutineScope.launch {
            generateMissionApplication = GenerateMissionApplication().startApplication(arrayOf(), properties) {
                text = "Position of entity: [" + generateMissionApplication?.entityPosition.toString() + "]"
            }
        }
    }

    Button(onClick = getGenerateMissionApplicationOnClick) {
        Text("detectGenerateMissionApplication")
    }
}