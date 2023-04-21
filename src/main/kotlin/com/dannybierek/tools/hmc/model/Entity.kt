package com.dannybierek.tools.hmc.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Entity(
    val blueprint: String?,
    val factory: String?,
    val properties: Properties?,
    val events: Map<String, Map<String, Any>>?,
    val outputCopying: Map<String, Map<String, Any>>?,
    val subsets: Map<String, List<String>>?,
    val name: String?,
    val parent: Any?,
    val template: String?
)