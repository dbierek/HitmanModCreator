package com.dannybierek.tools.hmc.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class MTransform(
    val type: String?,
    val value: MutableMap<String, Map<String, Any>>?
)