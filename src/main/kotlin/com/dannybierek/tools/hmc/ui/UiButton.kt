package com.dannybierek.tools.hmc.ui

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

class UiButton(
    text: String = "",
    primaryColor: Color = Color.Blue,
    secondaryColor: Color = Color.Green,
    clickHandler:() -> Unit = {},
    override var renderer: @Composable () -> Unit = @Composable {
        Button(onClick = {
            clickHandler()
        }) {
            MaterialTheme(
                lightColors().copy(
                    primary = primaryColor,
                    secondary = secondaryColor
                )
            ) {
                Text(text)
            }
        }
    }
) : UiElement