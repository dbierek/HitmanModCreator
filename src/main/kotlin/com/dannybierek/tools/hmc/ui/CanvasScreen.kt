package com.dannybierek.tools.hmc.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

data class CanvasScreen(
    var drawEntities: MutableList<DrawEntity> = mutableListOf(),
    override var renderer: @Composable () -> Unit = @Composable {
        Canvas2D(drawEntities)
    }
) : UiElement