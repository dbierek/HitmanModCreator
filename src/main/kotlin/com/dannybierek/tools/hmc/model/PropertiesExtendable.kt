package com.dannybierek.tools.hmc.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include

@JsonIgnoreProperties(ignoreUnknown = true)
open class PropertiesExtendable(
    var props: Map<String, PropertiesExtendable>
) : Properties()