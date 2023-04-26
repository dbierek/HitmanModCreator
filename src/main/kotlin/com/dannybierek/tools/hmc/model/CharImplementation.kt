package com.dannybierek.tools.hmc.model

data class CharImplementation(
    val blueprint: String = "",
    val factory: String = "",
    val name: String = "",
    val parent: String = "",
    val properties: PropertiesNpcActor = PropertiesNpcActor(),
    val subsets: Subsets = Subsets()
)