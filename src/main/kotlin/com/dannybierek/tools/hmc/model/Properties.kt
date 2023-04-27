package com.dannybierek.tools.hmc.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include

//@JsonIgnoreProperties(ignoreUnknown = true)
open class Properties(
    @JsonInclude(Include.NON_NULL)
    val RepositoryId: Map<String, Any>? = null,
    @JsonInclude(Include.NON_NULL)
    val m_mTransform: MTransform? = null,
    @JsonInclude(Include.NON_NULL)
    val m_OutfitRepositoryID: Map<String, Any>? = null,
    @JsonInclude(Include.NON_NULL)
    val m_nOutfitVariation: Map<String, Any>? = null,
    @JsonInclude(Include.NON_NULL)
    val m_nOutfitCharset: Map<String, Any>? = null,
    @JsonInclude(Include.NON_NULL)
    val m_eRequiredVoiceVariation: Map<String, Any>? = null,
    @JsonInclude(Include.NON_NULL)
    val m_bStartEnabled: Map<String, Any>? = null,
    @JsonInclude(Include.NON_NULL)
    val m_eidParent: Map<String, Any>? = null,
    val unknownProperties: Map<String, Any>? = mutableMapOf()
)