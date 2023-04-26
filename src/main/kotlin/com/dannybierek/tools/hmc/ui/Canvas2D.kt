package com.dannybierek.tools.hmc.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.PointerMatcher
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dannybierek.tools.hmc.model.Entity


@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class, ExperimentalTextApi::class)
@Composable
fun Canvas2D(drawEntities: MutableList<DrawEntity>) {

    val textMeasure = rememberTextMeasurer()
    for (i in 1..3) {
        drawEntities.add(
            DrawEntity(
                entity = Entity(),
                50F,
                50F,
                name = "DrawEntity $i"
            )
        )
    }
    var mousePosText by remember { mutableStateOf("Mouse: ") }
    var displayText by remember { mutableStateOf("Not Dragging: ") }
    var mousePos = Offset(0F, 0F)
    Canvas(
        modifier = Modifier
            .offset(400.dp, 250.dp)
            .fillMaxSize(0.5F)
            .onPointerEvent(PointerEventType.Move) {
                mousePos = it.changes.first().position
                mousePosText = "Mouse: $mousePos"
                for (drawEntity in drawEntities) {
                    if (drawEntity.active) {
                        drawEntity.color = Color.Green
                    }
                }

            }.onDrag(
                enabled = true,
                matcher = PointerMatcher.mouse(PointerButton.Primary),
                onDrag = {
                    displayText = "Mouse Dragging Coords: ${mousePos.x}, ${mousePos.y}"
                    for (drawEntity in drawEntities) {
                        drawEntity.x = mousePos.x
                        drawEntity.y = mousePos.y
                    }
                },
                onDragStart = {
                    for (drawEntity in drawEntities) {
                        drawEntity.color = Color.Blue
                    }
                    displayText = "Mouse Drag Start"
                },
                onDragEnd = {
                    for (drawEntity in drawEntities) {
                        drawEntity.color = Color.Red
                    }
                    displayText = "Mouse Drag End"
                })
            .combinedClickable(
                onClick = {
//                    displayText = "Click! ${count++}"
                },
                onDoubleClick = {
//                    displayText = "Double click! ${count++}"
                },
                onLongClick = {
//                    displayText = "Long click! ${count++}"
                }
            )
//            .onPointerEvent(PointerEventType.Enter) { entity.active = true }
//            .onPointerEvent(PointerEventType.Exit) { entity.active = false },
    ) {
        drawRect(Color.DarkGray, topLeft = Offset(0f, 0f), size = Size(this.size.width, this.size.height))
        for (drawEntity in drawEntities) {
            drawEntity.drawHandler(this, drawEntity)
        }
//        drawCircle(Color.Red, center = Offset(50f, 200f), radius = 40f)
//        drawLine(
//            Color.Green, Offset(20f, 0f),
//            Offset(200f, 200f), strokeWidth = 5f
//        )
        drawText(
            textMeasurer = textMeasure, text = mousePosText,
            style = TextStyle(
                fontSize = 35.sp,
                brush = Brush.linearGradient(
                    colors = listOf(Color.Red, Color.Blue)
                )
            ),
            topLeft = Offset(20.dp.toPx(), 20.dp.toPx())
        )
        drawText(
            textMeasurer = textMeasure, text = displayText,
            style = TextStyle(
                fontSize = 35.sp,
                brush = Brush.linearGradient(
                    colors = listOf(Color.Red, Color.Blue)
                )
            ),
            topLeft = Offset(20.dp.toPx(), 40.dp.toPx())
        )

//        drawArc(
//            Color.Black,
//            0f,
//            60f,
//            useCenter = true,
//            size = Size(300f, 300f),
//            topLeft = Offset(60f, 60f)
//        )
    }
}