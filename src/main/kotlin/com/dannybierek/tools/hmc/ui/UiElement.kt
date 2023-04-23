package com.dannybierek.tools.hmc.ui

import androidx.compose.runtime.Composable

interface UiElement {
    var renderer: @Composable () -> Unit
}