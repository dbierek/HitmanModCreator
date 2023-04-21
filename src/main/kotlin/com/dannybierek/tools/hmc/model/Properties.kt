package com.dannybierek.tools.hmc.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Properties(
    val RepositoryId: Map<String, Any>?,
    val m_mTransform: MTransform?,
    val m_OutfitRepositoryID: Map<String, Any>?,
    val m_nOutfitVariation: Map<String, Any>?,
    val m_nOutfitCharset: Map<String, Any>?,
    val m_eRequiredVoiceVariation: Map<String, Any>?,
    val m_bStartEnabled: Map<String, Any>?,
    val m_eidParent: Map<String, Any>?
)