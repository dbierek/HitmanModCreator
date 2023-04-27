package com.dannybierek.tools.hmc.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class MTransform(
    val type: String = "SMatrix43",
    val value: Value = Value()
) {

    fun setX(x: Float) {
        value.position.x = x
    }

    fun setY(y: Float) {
        value.position.y = y
    }

    fun setZ(z: Float) {
        value.position.z = z
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