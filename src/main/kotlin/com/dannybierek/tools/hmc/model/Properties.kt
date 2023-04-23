package com.dannybierek.tools.hmc.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Properties(
    val RepositoryId: Map<String, Any>? = null,
    val m_mTransform: MTransform = MTransform(),
    val m_OutfitRepositoryID: Map<String, Any>? = null,
    val m_nOutfitVariation: Map<String, Any>? = null,
    val m_nOutfitCharset: Map<String, Any>? = null,
    val m_eRequiredVoiceVariation: Map<String, Any>? = null,
    val m_bStartEnabled: Map<String, Any>? = null,
    val m_eidParent: Map<String, Any>? = null
)