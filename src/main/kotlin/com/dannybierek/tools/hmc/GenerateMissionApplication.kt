package com.dannybierek.tools.hmc

import com.dannybierek.tools.hmc.factories.FileFactory
import com.dannybierek.tools.hmc.model.QuickEntity
import com.fasterxml.jackson.databind.ObjectMapper
import io.klogging.Klogging
import io.klogging.Level
import io.klogging.config.DEFAULT_CONSOLE
import io.klogging.config.loggingConfiguration
import java.io.File
import java.io.IOException

class GenerateMissionApplication {
    var filename: String = ""
    var entityPosition:  Map<String, Any>? = mapOf()

    suspend fun startApplication(args: Array<String>, runUICallback: () -> Unit): GenerateMissionApplication {
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
        val npcsEntity = quickEntity.entities?.get(NPCS_HASH)
        logger.info { "NPCs Entity: " }
        logger.info { npcsEntity }
        val thomasCrossEntity = quickEntity.entities?.get(TC_HASH)
        var transform = thomasCrossEntity?.properties?.m_mTransform?.value
        var position = transform?.get("position")
        val thomasCrossEntity2 = thomasCrossEntity?.copy()
        var transform2 = thomasCrossEntity?.properties?.m_mTransform?.value
        transform2?.set("position", mapOf(Pair("x", 1), Pair("y", 2), Pair("z", 3)))
        var position2 = transform2?.get("position")
        logger.info { "thomasCrossEntity: " }
        logger.info { thomasCrossEntity }
        logger.info { "thomasCrossEntity Position " }
        logger.info {position}
        logger.info { "thomasCrossEntity2 Position " }
        logger.info {position2}
        entityPosition = position
        val path = "src/main/resources/scenes/scene_output.entity.json"
        try {
            val mapper = ObjectMapper()
            mapper.writeValue(File(path), quickEntity)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        logger.info { "Running UI Callback "}
        runUICallback()
        logger.info { "Finished running UI Callback "}
    return this

//        val file = ff.createFile(filename, FileType.TEMP)
//        file.writeText(ObjectMapper.)
    }

    private fun configureKlogging() {
        loggingConfiguration {
            DEFAULT_CONSOLE()
            minDirectLogLevel(Level.INFO)
        }
    }

    companion object : Klogging {
        const val NPCS_HASH: String = "abcde92f2c09c134"
        const val TC_HASH: String = "feed93e54821f99f"
    }
}
//
//fun main(args: Array<String>) = runBlocking {
//    GenerateMissionApplication().startApplication(args)
//}