package com.dannybierek.tools.hmc

import com.dannybierek.tools.hmc.config.GenerateMissionProperties
import com.dannybierek.tools.hmc.factories.FileFactory
import com.dannybierek.tools.hmc.mappers.JsonMapper
import com.dannybierek.tools.hmc.model.CharImplementation
import com.dannybierek.tools.hmc.model.Entity
import com.dannybierek.tools.hmc.model.Outfit
import com.dannybierek.tools.hmc.model.QuickEntity
import com.dannybierek.tools.hmc.model.Vector3d
import com.dannybierek.tools.hmc.ui.HeaderText
import mu.KLogging
import org.apache.log4j.BasicConfigurator
import java.io.IOException
import kotlin.math.floor
import kotlin.math.sqrt

class GenerateMissionApplication {
    private var runUICallback: (data: Vector3d) -> Unit = {}
    private var filenameArgument: String = ""
    private var entityPosition: Vector3d = Vector3d()
    private var quickEntity: QuickEntity = QuickEntity()
    private var outputSceneFile = "src/main/resources/scenes/scene_output.entity.json"
    var properties: GenerateMissionProperties = GenerateMissionProperties(
        sceneFile = "src/main/resources/scenes/scene_output.entity.json", statusTextElement = HeaderText()
    )
    private val jsonMapper = JsonMapper().mapper
    private val ff = FileFactory(jsonMapper)
    var args: Array<String> = arrayOf()

    fun startApplication(
        args: Array<String>, properties: GenerateMissionProperties, runUICallback: (data: Vector3d) -> Unit
    ): GenerateMissionApplication {
        configureLogging()
        properties.statusTextElement.text = "Hello from GenerateMissionApplication"
        this.args = args
        this.properties = properties
        this.runUICallback = runUICallback
        logger.info { "Starting com.dannybierek.tools.hmc.GenerateMissionApplication with args: [${args.toList()}]" }
        handleArguments(args)
        quickEntity = loadQuickEntity()
        showNpcOutfitsAndPositions()
//        moveThomasCross()
        saveToFile(outputSceneFile)
        return this
    }

    private fun showNpcOutfitsAndPositions() {
        var outfitNumber: MutableList<Int> = mutableListOf(0)
        logger.info {
            getEntity(OUTFITS_HASH)?.getChildren(OUTFITS_HASH, quickEntity)?.map {
                outfitNumber[0] += 1
                "Outfit number: ${outfitNumber[0]} Outfit: ${it.value.factory}\tPosition: ${it.value.properties?.m_mTransform?.value?.position}\n"
            }
        }
    }

    private fun saveToFile(path: String) {
        logger.info { "Saving to file: $path" }
        try {
            ff.saveQuickEntityJson(path, quickEntity)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun moveThomasCross() {
        logger.info { "Moving Thomas Cross:" }

        val npcsEntity = getEntity(NPCS_HASH)
        logger.info { "NPCs Entity: " }
        logger.info { npcsEntity }
        val thomasCrossEntity = getEntity(TC_HASH) ?: Entity()
        if (!quickEntity.entities.contains(TC_HASH)) {
            logger.error { "Thomas Cross entity not found. Creating new Entity" }
        }

        var thomasCrossTransform = thomasCrossEntity.properties?.m_mTransform
        val uiEntity0 = properties.drawEntities[0]
        entityPosition = uiEntity0.properties?.m_mTransform?.value?.position ?: Vector3d()
        logger.info { "thomasCrossEntity Before: " }
        logger.info { thomasCrossEntity }
        logger.info { "thomasCrossEntity Before Position " }
        logger.info { thomasCrossTransform?.value?.position }
        logger.info { "uiEntity0: " }
        logger.info { uiEntity0 }
        logger.info { "uiEntity0 Position " }
        thomasCrossTransform?.value?.position = entityPosition
        logger.info { entityPosition }
        logger.info { "thomasCrossEntity After: " }
        logger.info { thomasCrossEntity }
        logger.info { "thomasCrossEntity After Position " }
    }

    private fun getEntity(hash: String): Entity? {
        logger.info { "Getting entity: $hash" }
        return quickEntity.entities[hash]
    }

    private fun loadQuickEntity(): QuickEntity {
        logger.info { "Loading QuickEntity from filename: ${properties.sceneFile}" }
        return ff.loadJsonQuickEntity(properties.sceneFile)
    }

    private fun handleArguments(args: Array<String>) {
        logger.info { "Handling Arguments: ${args.toList()}" }

        for (arg in args) {
            logger.info { arg }

            if (arg.take(10) == "-file=" || arg.take(3) == "-f=") {
                filenameArgument = arg.substring(10)
            }
        }
    }

    fun createNpcGrid(outfits: List<Outfit>): MutableList<CharImplementation> {
        logger.info { "Creating NPC Grid: $outfits" }

        val numRows = floor(sqrt(outfits.size.toDouble()))
        var x = 0.0
        var y = 0.0
        val npcEntities: MutableList<CharImplementation> = mutableListOf()
        outfits.indices.forEachIndexed { index, outfit ->
            run {
                val npc = CharImplementation()
                npcEntities.add(npc)
            }
        }
        return npcEntities
    }

    private fun configureLogging() {
        BasicConfigurator.configure();
        logger.info { "Configuring Logging" }

//        loggingConfiguration {
//            DEFAULT_CONSOLE()
//            minDirectLogLevel(Level.INFO)
//        }
    }

    companion object : KLogging() {
        const val NPCS_HASH: String = "abcde92f2c09c134"
        const val TC_HASH: String = "feed93e54821f99f"
        const val OUTFITS_HASH: String = "abcdecaf31f72bb4"
    }
}
//
//fun main(args: Array<String>) = runBlocking {
//    GenerateMissionApplication().startApplication(args)
//}