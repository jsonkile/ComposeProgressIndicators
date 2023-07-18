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

fun DrawScope.StepsProgressIndicator2(
    shadedAreaColor: Color = Color.LightGray,
    unShadedAreaColor: Color = Color.DarkGray,
    progress: Int = 3,
    makeLineEndsFlat: Boolean = true,
    radius: Dp = 8.dp,
    lineHeight: Dp = 2.dp,
    steps: Int = 7
) {
    if (progress !in 0..steps) {
        throw IllegalStateException("progress should be not be greater than the number of steps")
    }

    if (steps < 2) {
        throw IllegalStateException("steps should be not be less than zero")
    }

    val spacing = (size.width - (radius.toPx() * 2)) / (steps - 1)

    drawLine(
        start = Offset(x = 0F + radius.toPx(), y = size.height / 2),
        end = Offset(x = size.width - radius.toPx(), y = size.height / 2),
        strokeWidth = lineHeight.toPx(),
        color = unShadedAreaColor,
        cap = if (makeLineEndsFlat) StrokeCap.Butt else StrokeCap.Round
    )

    fun drawStepBall(step: Int = 0) {
        if (step < steps) {
            val xPosition = (0F + radius.toPx()) + (spacing * step)

            if (step < progress - 1) {
                drawLine(
                    start = Offset(x = xPosition, y = size.height / 2),
                    end = Offset(
                        x = (0F + radius.toPx()) + (spacing * (step + 1)),
                        y = size.height / 2
                    ),
                    strokeWidth = lineHeight.toPx(),
                    color = shadedAreaColor,
                    cap = StrokeCap.Butt
                )
            }

            drawCircle(
                center = Offset(x = xPosition, y = size.height / 2),
                color = if (progress > step) shadedAreaColor else unShadedAreaColor,
                radius = radius.toPx()
            )

            drawStepBall(step = step + 1)
        }
    }

    drawStepBall()
}

@Preview
@Composable
internal fun StepsProgressIndicator2Preview() {
    Canvas(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(20.dp)
    ) {
        StepsProgressIndicator2()
    }
}