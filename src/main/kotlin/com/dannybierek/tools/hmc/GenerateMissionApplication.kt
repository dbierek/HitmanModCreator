package com.dannybierek.tools.hmc

import com.dannybierek.tools.hmc.config.GenerateMissionProperties
import com.dannybierek.tools.hmc.factories.FileFactory
import com.dannybierek.tools.hmc.model.Entity
import com.dannybierek.tools.hmc.model.QuickEntity
import com.dannybierek.tools.hmc.model.Vector3d
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KLogging
import java.io.File
import java.io.IOException

class GenerateMissionApplication {
    var filename: String = ""
    var entityPosition: Vector3d = Vector3d()

    fun startApplication(args: Array<String>, properties: GenerateMissionProperties, runUICallback: (data: Vector3d) -> Unit): GenerateMissionApplication {
        configureKlogging()
        logger.info { "Starting com.dannybierek.tools.hmc.GenerateMissionApplication with args:" }
        for (arg in args) {
            logger.info { arg }

            if (arg.take(10) == "-file=" || arg.take(3) == "-f=") {
                filename = arg.substring(10)
            }
        }
        logger.info { "Filename: $filename" }
        val ff = FileFactory()
        val quickEntity: QuickEntity = ff.jsonFileToQuickEntity(filename)
        val npcsEntity = quickEntity.entities[NPCS_HASH]
        logger.info { "NPCs Entity: " }
        logger.info { npcsEntity }
        val thomasCrossEntity = quickEntity.entities[TC_HASH] ?: Entity()
        if (!quickEntity.entities.contains(TC_HASH)) {
            logger.error { "Thomas Cross entity not found. Creating new Entity" }
        }

        var thomasCrossTransform = thomasCrossEntity.properties.m_mTransform
        val uiEntity0 = properties.entities[0]
        entityPosition = uiEntity0.properties.m_mTransform.getPosition()
        logger.info { "thomasCrossEntity: " }
        logger.info { thomasCrossEntity }
        logger.info { "thomasCrossEntity Position " }
        logger.info { thomasCrossTransform.getPosition() }
        logger.info { "uiEntity0: " }
        logger.info { uiEntity0 }
        logger.info { "uiEntity0 Position " }
        thomasCrossTransform.setPosition(entityPosition)
        logger.info { entityPosition}
        logger.info { "thomasCrossEntity: " }
        logger.info { thomasCrossEntity }
        logger.info { "thomasCrossEntity Position " }
        val path = "src/main/resources/scenes/scene_output.entity.json"
        try {
            val mapper = ObjectMapper()
            mapper.writeValue(File(path), quickEntity)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    return this

//        val file = ff.createFile(filename, FileType.TEMP)
//        file.writeText(ObjectMapper.)
    }

    private fun configureKlogging() {
//        loggingConfiguration {
//            DEFAULT_CONSOLE()
//            minDirectLogLevel(Level.INFO)
//        }
    }

    companion object : KLogging() {
        const val NPCS_HASH: String = "abcde92f2c09c134"
        const val TC_HASH: String = "feed93e54821f99f"
    }
}
//
//fun main(args: Array<String>) = runBlocking {
//    GenerateMissionApplication().startApplication(args)
//}