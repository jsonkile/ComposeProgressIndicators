package com.jsonkile.composeprogress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.jsonkile.composeprogressindicators.BallScaleProgressIndicator

@androidx.compose.ui.tooling.preview.Preview
@androidx.compose.runtime.Composable
fun Previews() {
    Canvas(Modifier.fillMaxSize()) {
        BallScaleProgressIndicator()
    }
}