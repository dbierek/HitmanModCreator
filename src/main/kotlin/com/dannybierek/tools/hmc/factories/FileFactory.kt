package com.dannybierek.tools.hmc.factories

import com.dannybierek.tools.hmc.mappers.JsonMapper
import com.dannybierek.tools.hmc.model.FileType
import com.dannybierek.tools.hmc.model.QuickEntity
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import com.fasterxml.jackson.module.kotlin.readValue
import mu.KLogging
import java.io.File


class FileFactory(private val jsonMapper: ObjectMapper = JsonMapper().mapper) {
    fun loadJsonQuickEntity(filename: String): QuickEntity {
        logger.info { "Loading file: $filename" }
        return try {
            val quickEntity: QuickEntity = jsonMapper.readValue(File(filename))
            logger.info { quickEntity }
            quickEntity
        } catch (e: UnrecognizedPropertyException) {
            logger.error {"Issue in deserialzing: $e" }
            logger.error { e.message }
            QuickEntity()
        }
    }

    fun saveQuickEntityJson(filename: String, quickEntity: QuickEntity) {
        try {
            logger.info { "Saving file: $filename"}
            jsonMapper.writeValue(File(filename), quickEntity)
        } catch (e: Exception) {
            logger.error {"Issue saving file: $e" }
            logger.error { e.message }
        }
    }

    fun createFile(fileName: String, fileType: FileType): File {
        val file = File(fileName)
        logger.info { "Attempting to create file: $fileName of type $fileType" }
        val success: Boolean = file.createNewFile()

        if (success) {
            logger.info { "$fileName created successfully." }
        } else {
            logger.info { "$fileName already exists." }
        }
        logger.info { "Files in scenes directory: "}
        File("src/resources/scenes").walkTopDown().forEach {
            logger.info { it }
        }
        return file
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