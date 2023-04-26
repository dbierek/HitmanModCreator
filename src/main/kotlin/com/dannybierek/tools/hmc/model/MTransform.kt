package com.dannybierek.tools.hmc.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class MTransform(
    val type: String = "SMatrix43",
    val value: MutableMap<String, MutableMap<String, Float>> = mutableMapOf(
        Pair(
            "position", mutableMapOf(
                Pair("x", 0F),
                Pair("y", 0F),
                Pair("z", 0F)
            )
        ),
        Pair(
            "rotation", mutableMapOf(
                Pair("x", 0F),
                Pair("y", 0F),
                Pair("z", 0F)
            )
        )
    )
) {

    fun setX(x: Float) {
        value["position"]!!["x"] = x
    }

    fun setY(y: Float) {
        value["position"]!!["y"] = y
    }

    fun setZ(z: Float) {
        value["position"]!!["z"] = z
    }
    fun setPosition(position: Vector3d) {
        value["position"]!!["x"] = position.x
        value["position"]!!["y"] = position.y
        value["position"]!!["z"] = position.z
    }
    fun setPosition(x: Float, y: Float, z: Float) {
        value["position"]!!["x"] = x
        value["position"]!!["y"] = y
        value["position"]!!["z"] = z
    }

    fun getPosition(): Vector3d {
        return Vector3d(
            value["position"]!!["x"]!!,
            value["position"]!!["y"]!!,
            value["position"]!!["z"]!!
        )
    }

//    var testJson = """
//        "m_mTransform": {
//          "type": "SMatrix43",
//          "value": {
//            "rotation": {
//              "x": 0,
//              "y": 0,
//              "z": 0
//            },
//            "position": {
//              "x": -55,
//              "y": 110,
//              "z": 12
//            }
//          }
//        """.trimIndent()
}