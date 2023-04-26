package com.dannybierek.tools.hmc.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.dannybierek.tools.hmc.GenerateMissionApplication
import com.dannybierek.tools.hmc.config.GenerateMissionProperties

class GenerateMissionButton(
    primaryColor: Color = Color.Blue,
    secondaryColor: Color = Color.Green,
    drawEntities: List<DrawEntity> = mutableListOf(),
    var statusTextElement: HeaderText = HeaderText(),
    override var renderer: @Composable () -> Unit = @Composable {
        UiButton(
            "Create Mission",
            primaryColor = primaryColor,
            secondaryColor = secondaryColor,
            clickHandler = {
                GenerateMissionApplication().startApplication(
                    arrayOf(),
                    GenerateMissionProperties(sceneFile = "", statusTextElement = statusTextElement, drawEntities = drawEntities.map { it.entity }
                        .toMutableList())
                ) {
//                    statusTextElement.text = "Position of entity: [$it]"
                }
            })
    }
) : UiElement