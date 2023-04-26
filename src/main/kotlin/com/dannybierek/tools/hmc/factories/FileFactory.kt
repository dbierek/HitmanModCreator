package com.dannybierek.tools.hmc.factories

import com.dannybierek.tools.hmc.model.FileType
import com.dannybierek.tools.hmc.model.QuickEntity
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import mu.KLogging
import java.io.File


class FileFactory {
    fun createFile(fileName: String, fileType: FileType): File {
        val file = File(fileName)
        logger.info { "Attempting to create file: $fileName of type $fileType" }
        val success: Boolean = file.createNewFile()

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
    fun jsonFileToQuickEntity(filename: String): QuickEntity {
        logger.info { "Loading file: $filename" }
        return try {
            val quickEntity: QuickEntity = getJsonMapper().readValue(File(filename))
            logger.info { quickEntity }
            quickEntity
        } catch (e: JsonMappingException) {
            logger.info {"Issue in deserializing: $e" }
            QuickEntity()
        }
    }

    private fun getJsonMapper(): ObjectMapper {
        var mapper1 = jacksonObjectMapper().registerModule(KotlinModule(nullToEmptyMap = true))
        mapper1.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper1
    }

    fun editFile(fileName: String, fileType: FileType) {
        val file = File(fileName)
        logger.info { "Attempting to create file: $fileName of type $fileType" }
        val success: Boolean = file.createNewFile()

        if (success) {
            logger.info { "$fileName created successfully." }
        } else {
            logger.info { "$fileName already exists." }
        }
    }
    companion object : KLogging()
}