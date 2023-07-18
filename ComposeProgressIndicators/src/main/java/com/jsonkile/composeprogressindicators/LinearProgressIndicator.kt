package com.jsonkile.composeprogressindicators

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * Call this function in a drawScope like a Canvas' onDraw
 * @param progress enter a value between 0F to 1F
 * @param makeEndsFlat should make the end of the bars flat
 */
fun DrawScope.LinearProgressIndicator(
    backgroundBarThickness: Dp = 5.dp,
    progressBarThickness: Dp = 10.dp,
    progress: Float = 0.3F,
    backgroundBarColor: Color = Color.DarkGray,
    progressBarColor: Color = Color.LightGray,
    makeEndsFlat: Boolean = false
) {

    if (progress !in 0F..1F) {
        throw IllegalStateException("progress should be between 0F and 1F")
    }

    drawLine(
        start = Offset(x = 0F, y = size.height / 2),
        end = Offset(x = size.width, y = size.height / 2),
        color = backgroundBarColor,
        strokeWidth = backgroundBarThickness.toPx(),
        cap = if (makeEndsFlat) StrokeCap.Square else StrokeCap.Round
    )

    drawLine(
        start = Offset(x = 0F, y = size.height / 2),
        end = Offset(x = size.width * progress, y = size.height / 2),
        color = progressBarColor,
        strokeWidth = progressBarThickness.toPx(),
        cap = if (makeEndsFlat) StrokeCap.Square else StrokeCap.Round
    )
}

@Preview
@Composable
internal fun LinearProgressIndicatorPreview() {
    Canvas(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(10.dp)
    ) {
        LinearProgressIndicator()
    }
}