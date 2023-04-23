package com.dannybierek.tools.hmc.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class DropDownList(
    items: List<String> = listOf("TEMP", "AIRG", "TBLU", "PRIM", "ASET", "BORG"),
    primaryColor: Color,
    secondaryColor: Color,
    override var renderer: @Composable () -> Unit = @Composable {
        var expanded by remember { mutableStateOf(false) }
        val disabledValue = "BORG"
        var selectedIndex by remember { mutableStateOf(0) }
        Box(modifier = Modifier.fillMaxSize(.5F).wrapContentSize(Alignment.TopStart)) {
            Text(
                items[selectedIndex],
                modifier = Modifier.fillMaxWidth().clickable(onClick = { expanded = true }).background(
                    primaryColor
                )
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth().background(
                    secondaryColor
                )
            ) {
                items.forEachIndexed { index, s ->
                    DropdownMenuItem(onClick = {
                        selectedIndex = index
                        expanded = false
                    }) {
                        val disabledText = if (s == disabledValue) {
                            " (Disabled)"
                        } else {
                            ""
                        }
                        Text(text = s + disabledText)
                    }
                }
            }
        }
    }
) : UiElement
