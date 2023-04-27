package com.dannybierek.tools.hmc.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

//@JsonIgnoreProperties(ignoreUnknown = true)
data class Entity(
    val parent: String? = "",
    val name: String? = null,
    val blueprint: String? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val properties: Properties? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val subsets: Map<String, List<String>>? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val events: Map<String, Map<String, Any>>? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val outputCopying: Map<String, Map<String, Any>>? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val template: String? = null,
    val factory: String? = null
) {
    fun getChildren(entityId: String, scene: QuickEntity) = scene.entities.filter { it.value.parent == entityId }
}