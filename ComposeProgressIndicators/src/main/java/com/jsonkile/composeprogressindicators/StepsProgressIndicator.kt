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

fun DrawScope.StepsProgressIndicator(
    shadedAreaColor: Color = Color.LightGray,
    unShadedAreaColor: Color = Color.DarkGray,
    progress: Int = 1,
    makeLineEndsFlat: Boolean = false,
    radius: Dp = 8.dp,
    lineHeight: Dp = 2.dp,
    steps: Int = 3
) {
    if (progress > steps) {
        throw IllegalStateException("progress should be not be greater than the number of steps")
    }

    if (steps < 1) {
        throw IllegalStateException("steps should be not be less than zero")
    }

    val spacing = size.width / steps

    drawLine(
        start = Offset(x = 0F, y = size.height / 2),
        end = Offset(x = size.width, y = size.height / 2),
        strokeWidth = lineHeight.toPx(),
        color = unShadedAreaColor,
        cap = if (makeLineEndsFlat) StrokeCap.Butt else StrokeCap.Round
    )

    var lastBallXPosition = 0F

    for (i in 1..steps) {
        val xPosition = if (i == 1) (spacing / 2) * i else lastBallXPosition + spacing

        drawLine(
            start = Offset(
                x = if (lastBallXPosition != 0F) lastBallXPosition + radius.toPx() else lastBallXPosition,
                y = size.height / 2
            ),
            end = Offset(x = xPosition, y = size.height / 2),
            strokeWidth = lineHeight.toPx(),
            color = if (progress >= i) shadedAreaColor else unShadedAreaColor,
            cap = if (i == 1 && !makeLineEndsFlat) StrokeCap.Round else StrokeCap.Butt
        )

        drawCircle(
            center = Offset(x = xPosition, y = size.height / 2),
            color = if (progress >= i) shadedAreaColor else unShadedAreaColor,
            radius = radius.toPx()
        )

        lastBallXPosition = xPosition

        if (i == steps && progress == steps) {
            drawLine(
                start = Offset(x = xPosition + radius.toPx(), y = size.height / 2),
                end = Offset(x = size.width, y = size.height / 2),
                strokeWidth = lineHeight.toPx(),
                color = shadedAreaColor,
                cap = if (makeLineEndsFlat) StrokeCap.Butt else StrokeCap.Round
            )
        }
    }
}

@Preview
@Composable
internal fun StepsProgressIndicatorPreview() {
    Canvas(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(20.dp)
    ) {
        StepsProgressIndicator()
    }
}