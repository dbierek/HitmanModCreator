package com.dannybierek.tools.hmc.model

data class PropertiesNpcActor(
    val RepositoryId: RepositoryId = RepositoryId(),
    val m_OutfitRepositoryID: MOutfitRepositoryID = MOutfitRepositoryID(),
    val m_bStartEnabled: MBStartEnabled = MBStartEnabled(),
    val m_eRequiredVoiceVariation: MERequiredVoiceVariation = MERequiredVoiceVariation(),
    val m_eidParent: MEidParent = MEidParent(),
    val m_mTransform: MTransform = MTransform(),
    val m_nOutfitCharset: MNOutfitCharset = MNOutfitCharset(),
    val m_nOutfitVariation: MNOutfitVariation = MNOutfitVariation(),
    val m_sActorName: MSActorName = MSActorName()
)