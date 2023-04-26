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
import com.dannybierek.tools.hmc.ui.GenerateMissionButton
import com.dannybierek.tools.hmc.ui.HeaderText
import com.dannybierek.tools.hmc.ui.Image
import com.dannybierek.tools.hmc.ui.TabScreen
import com.dannybierek.tools.hmc.ui.UiElement
import java.awt.SystemColor.text


@Composable
@Preview
fun App() {
    TabScreen().renderer()
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}