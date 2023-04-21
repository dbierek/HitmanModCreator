package com.dannybierek.tools.hmc.factories

import com.dannybierek.tools.hmc.model.FileType
import com.dannybierek.tools.hmc.model.QuickEntity
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.klogging.Klogging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File


class FileFactory {
    suspend fun createFile(fileName: String, fileType: FileType): File {
        val file = File(fileName)
        logger.info { "Attempting to create file: $fileName of type $fileType" }
        val success: Boolean = withContext(Dispatchers.IO) {
            file.createNewFile()
        }

        if (success) {
            logger.info { "$fileName created successfully." }
        } else {
            logger.info { "$fileName already exists." }
        }
        logger.info { "Files in scenese directory: "}
        File("src/resources/scenes").walkTopDown().forEach {
            logger.info { it }
        }
        return file
    }
    suspend fun jsonFileToQuickEntity(filename: String): QuickEntity {
        val mapper = jacksonObjectMapper().registerModule(
            KotlinModule(nullToEmptyMap = true)
        )
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        logger.info { "filename passed in: $filename" }
//        logger.info { "filename to load: \"src/main/resources/sceneTemplate.entity.json\"" }
        val filename2: String = "src/main/resources/scenes/scene_debug.entity.json"
        logger.info { "filename to load: $filename2" }

        var quickEntity: QuickEntity? = null
        try {
            quickEntity = mapper.readValue(File(filename2))
        } catch (e: JsonMappingException) {
            logger.info {"Issue in deserializing: $e" }
        }

        logger.info { quickEntity }
        return quickEntity ?: QuickEntity()
    }
    suspend fun editFile(fileName: String, fileType: FileType) {
        val file = File(fileName)
        logger.info { "Attempting to create file: $fileName of type $fileType" }
        val success: Boolean = withContext(Dispatchers.IO) {
            file.createNewFile()
        }

        if (success) {
            logger.info { "$fileName created successfully." }
        } else {
            logger.info { "$fileName already exists." }
        }
    }
    companion object : Klogging
}