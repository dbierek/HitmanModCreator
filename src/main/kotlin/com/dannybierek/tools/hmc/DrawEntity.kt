package com.dannybierek.tools.hmc

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

data class DrawEntity(var x: Float = 0F, var y: Float = 0F, var drawHandler: (DrawScope, DrawEntity) -> Unit, var color: Color = Color.Red) {

    var active: Boolean = false
    var dragging: Boolean = false
}
