package com.dannybierek.tools.hmc.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
data class QuickEntity(
    val comments: List<Any>? = null,
    val entities: MutableMap<String?, Entity> = mutableMapOf(),
    val externalScenes: List<Any>? = null,
    val extraBlueprintDependencies: List<Any>? = null,
    val extraFactoryDependencies: List<Any>? = null,
    val overrideDeletes: List<Any>? = null,
    val pinConnectionOverrideDeletes: List<Any>? = null,
    val pinConnectionOverrides: List<Any>? = null,
    val propertyOverrides: List<Any>? = null,
    val quickEntityVersion: Int? = null,
    val rootEntity: String? = null,
    val subType: String? = null,
    val tbluHash: String? = null,
    val tempHash: String? = null
)