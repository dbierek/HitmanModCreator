package com.dannybierek.tools.hmc.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
data class Entity(
    val blueprint: String? = null,
    val factory: String? = null,
    val properties: Properties = Properties(),
    val events: Map<String, Map<String, Any>>? = null,
    val outputCopying: Map<String, Map<String, Any>>? = null,
    val subsets: Map<String, List<String>>? = null,
    val name: String? = null,
    val parent: Any? = null,
    val template: String? = null
)