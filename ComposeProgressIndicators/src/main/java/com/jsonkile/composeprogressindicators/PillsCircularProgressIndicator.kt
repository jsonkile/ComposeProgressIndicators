package com.jsonkile.composeprogressindicators

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @param progress the pill to shade down from
 * @param totalNumberOfPills the number of pills the indicator should have
 * @param shadedPillsColor the color of shaded pills
 * @param unshadedPillsColor the color of unshaded pills
 * @param useSharpEndedPills whether to use sharp pills instead of rounded ones
 * @param rotationDegree clockwise rotation magnitude
 */
fun DrawScope.PillsCircularProgressIndicator(
    progress: Int = 3,
    totalNumberOfPills: Int = 55,
    shadedPillsColor: Color = Color.Green,
    unshadedPillsColor: Color = Color.Green.copy(alpha = .3F),
    useSharpEndedPills: Boolean = true,
    pillsWidth: Dp = 5.dp,
    rotationDegree: Float = 0F
) {
    for (i in 0 until totalNumberOfPills) {
        val startAngle = i * (360F / totalNumberOfPills)
        val sweepAngle = (360F / totalNumberOfPills) * 0.89F

        rotate(degrees = -90f + rotationDegree) {
            drawArc(
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                color = if (i < progress) shadedPillsColor else unshadedPillsColor,
                style = Stroke(
                    width = pillsWidth.toPx(),
                    cap = if (useSharpEndedPills) StrokeCap.Butt else StrokeCap.Round
                )
            )
        }
    }
}

@Preview
@Composable
internal fun PillsCircularProgressIndicatorPreview() {
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .padding(5.dp)
    ) {
        PillsCircularProgressIndicator()
    }
}