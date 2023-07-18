package com.jsonkile.composeprogressindicators

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Call this function in a drawScope like a Canvas' onDraw
 * @param progress the pill to shade down from
 * @param numberOfDots the number of pills the indicator should have
 * @param shadedColor the color of shaded balls
 * @param unshadedColor the color of unshaded balls
 * @param spacing specify the spaces between balls
 */
fun DrawScope.BallsLinearProgressIndicator(
    progress: Int = 3,
    numberOfDots: Int = 10,
    shadedColor: Color = Color.Green,
    unshadedColor: Color = Color.Green.copy(alpha = .3F),
    spacing: Dp = 9.dp
) {

    if (progress > numberOfDots) {
        throw IllegalStateException("progress should be not be greater than the number of balls")
    }

    val ballArea = size.width / numberOfDots
    val ballDiameter = ballArea - spacing.toPx()

    var nextBallStartPosition = 0F

    for (i in 0..numberOfDots) {
        val ballStartPosition = nextBallStartPosition + (spacing / 2).toPx()
        val ballEndPosition = ballStartPosition + ballDiameter

        val start = Offset(x = ballStartPosition, y = size.height / 2)
        val end = Offset(x = ballEndPosition, y = size.height / 2)

        drawCircle(
            color = if (i < progress) shadedColor else unshadedColor,
            radius = ballDiameter / 2,
            center = start.plus(end) / 2F
        )

        nextBallStartPosition = ballEndPosition + (spacing / 2).toPx()
    }
}

@Preview
@Composable
internal fun BallsLinearProgressIndicatorPreview() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
    ) {
        BallsLinearProgressIndicator()
    }
}