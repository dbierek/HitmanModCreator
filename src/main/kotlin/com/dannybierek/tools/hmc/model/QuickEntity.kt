package com.dannybierek.tools.hmc.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
data class QuickEntity(
    val tempHash: String = "",
    val tbluHash: String = "",
    val rootEntity: String = "",
    val entities: MutableMap<String, Entity> = mutableMapOf(),
    val propertyOverrides: List<Any> = listOf(),
    val overrideDeletes: List<Any> = listOf(),
    val pinConnectionOverrides: List<Any> = listOf(),
    val pinConnectionOverrideDeletes: List<Any> = listOf(),
    val externalScenes: List<String> = listOf(),
    val subType: String? = SubType.scene.toString(),
    val quickEntityVersion: Double = 3.1,
    val extraFactoryDependencies: List<Any> = listOf(),
    val extraBlueprintDependencies: List<Any> = listOf(),
    val comments: List<String>? = listOf()
)