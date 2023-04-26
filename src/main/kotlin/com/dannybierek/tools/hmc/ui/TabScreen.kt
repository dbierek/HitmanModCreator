package com.dannybierek.tools.hmc.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

data class TabScreen(
    val tabs: List<String> = listOf("Home", "Canvas"),
    override var renderer: @Composable () -> Unit =
    @Composable
    {
        var tabIndex by remember { mutableStateOf(0) }

        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            when (tabIndex) {
                0 -> HomeScreen().renderer()
                1 -> CanvasScreen().renderer()
            }
        }
    }
) : UiElement