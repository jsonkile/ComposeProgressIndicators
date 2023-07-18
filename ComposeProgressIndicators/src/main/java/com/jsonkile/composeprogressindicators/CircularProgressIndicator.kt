package com.jsonkile.composeprogressindicators

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @param progress the pill to shade down from
 * @param makeEndsOfShadedArcSharp whether to use sharp pills instead of rounded ones
 * @param fillCircle should fill the background
 */
fun DrawScope.CircularProgressIndicator(
    progress: Float = 0.9F,
    shadedArcColor: Color = Color.Green,
    unshadedArcColor: Color = Color.Green.copy(alpha = .5F),
    makeEndsOfShadedArcSharp: Boolean = false,
    shadedArcThickness: Dp = 8.dp,
    unshadedArcThickness: Dp = 8.dp,
    fillCircle: Boolean = false
) {

    if (progress !in 0F..1F) {
        throw IllegalStateException("progress should be between 0F and 1F")
    }

    val startAngle = 0F
    val sweepAngle = 360F * progress

    drawCircle(
        color = unshadedArcColor,
        radius = size.height / 2,
        style = if (fillCircle) Fill else Stroke(width = unshadedArcThickness.toPx()),
        center = Offset(size.width / 2, size.height / 2)
    )

    rotate(degrees = -90f) {
        drawArc(
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            color = shadedArcColor,
            style = Stroke(
                width = shadedArcThickness.toPx(),
                cap = if (makeEndsOfShadedArcSharp) StrokeCap.Square else StrokeCap.Round
            )
        )
    }
}

@Preview
@Composable
internal fun CircularProgressIndicatorPreview() {
    Canvas(
        modifier = Modifier
            .padding(5.dp)
            .size(100.dp)
    ) {
        CircularProgressIndicator()
    }
}