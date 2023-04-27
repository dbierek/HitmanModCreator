package com.dannybierek.tools.hmc.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.dannybierek.tools.hmc.model.Entity

data class DrawEntity(
    val entity: Entity = Entity(),
    var x: Float = 0F,
    var y: Float = 0F,
    var drawHandler: (DrawScope, DrawEntity) -> Unit =
        fun(drawScope: DrawScope, entity: DrawEntity) {
            drawScope.drawCircle(
                entity.color, center = Offset(
                    entity.x, entity.y
                ), radius = 40f
            )
        },
    var name: String = "DrawEntity",
    var color: Color = Color.Red
) {
    fun setEntityX(value: Float) {
        x = value
        entity.properties?.m_mTransform?.setX(value)
    }
    fun setEntityY(value: Float) {
        y = value
        entity.properties?.m_mTransform?.setY(value)
    }

    var active: Boolean = false
    var dragging: Boolean = false
}
