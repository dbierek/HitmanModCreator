package com.dannybierek.tools.hmc.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

data class Image(
    var fileName: String = "",
    var imageModifier: Modifier = Modifier.fillMaxWidth(),
    override var renderer: @Composable () -> Unit = @Composable {
        Image(
            painter = painterResource(fileName),
            contentDescription = "image",
            imageModifier,
            contentScale = ContentScale.Fit
        )
    }
) : UiElement