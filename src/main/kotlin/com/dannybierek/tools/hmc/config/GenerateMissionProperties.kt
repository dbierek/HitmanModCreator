package com.dannybierek.tools.hmc.config

import com.dannybierek.tools.hmc.model.Entity
import com.dannybierek.tools.hmc.ui.HeaderText

data class GenerateMissionProperties(
    val drawEntities: MutableList<Entity> = mutableListOf(),
    val sceneFile: String = "",
    val statusTextElement: HeaderText
)
