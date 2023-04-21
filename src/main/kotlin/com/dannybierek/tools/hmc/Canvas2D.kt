package com.dannybierek.tools.hmc

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class, ExperimentalTextApi::class)
@Composable
fun Canvas2D() {

    val textMeasure = rememberTextMeasurer()
    val entity = DrawEntity(
        50F, 50F, fun(drawScope: DrawScope, entity: DrawEntity) {
            drawScope.drawCircle(
                entity.color, center = Offset(
                    entity.x, entity.y
                ), radius = 40f
            )
        }
    )
    var mousePosText by remember { mutableStateOf("Mouse: ") }
    var displayText by remember { mutableStateOf("Not Dragging: ") }
    var mousePos = Offset(0F, 0F)
    Canvas(
        modifier = Modifier
            .offset(250.dp, 250.dp)
            .fillMaxSize(0.5F)
            .onPointerEvent(PointerEventType.Move) {
                mousePos = it.changes.first().position
                mousePosText = "Mouse: $mousePos"
                if (entity.active) {
                    entity.color = Color.Green
                }

            }.onDrag(
                enabled = true,
                matcher = PointerMatcher.mouse(PointerButton.Primary),
                onDrag = {
                    displayText = "Mouse Dragging Coords: ${mousePos.x}, ${mousePos.y}"
                    entity.x = mousePos.x
                    entity.y = mousePos.y
                },
                onDragStart = {
                    entity.color = Color.Blue
                    displayText = "Mouse Drag Start"
                },
                onDragEnd = {
                    entity.color = Color.Red
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
        entity.drawHandler(this, entity)
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