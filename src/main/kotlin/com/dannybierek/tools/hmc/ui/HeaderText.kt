package com.dannybierek.tools.hmc.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

data class HeaderText(
    var text: String = "",
    override var renderer: @Composable () -> Unit = @Composable {
        Text(
            text = text,
            style = TextStyle(fontSize = 20.sp)
        )
    }
) : UiElement