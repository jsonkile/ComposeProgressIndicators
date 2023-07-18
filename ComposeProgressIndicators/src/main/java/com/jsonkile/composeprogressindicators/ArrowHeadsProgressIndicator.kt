package com.jsonkile.composeprogressindicators

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.tan


/**
 * Call this function in a drawScope like a Canvas' onDraw
 * @param inclinationInDegrees degree of slope for the arrow head, should be between 0 to 60 degrees
 * @param progress enter a value between 0F to 1F
 * @param shadedColor color of the shaded parts
 * @param unshadedColor color of the unshaded parts
 * @param spacing space between arrows. the spacing may push the last arrow slightly out of view
 * @param makeEndsSharp sharp or rounded arrow points
 */
fun DrawScope.ArrowHeadsProgressIndicator(
    inclinationInDegrees: Float,
    progress: Float,
    shadedColor: Color,
    unshadedColor: Color,
    arrowThickness: Dp,
    spacing: Dp,
    makeEndsSharp: Boolean = false
) {
    if (inclinationInDegrees !in 0.0..60.0) {
        throw IllegalStateException("inclination should be between 0 and 60 degrees")
    }

    if (progress !in 0F..1F) {
        throw IllegalStateException("progress should be between 0F and 1F")
    }

    val arrowWidth = abs(tan(inclinationInDegrees * (3.14F / 180F)) * (size.height / 2))

    fun drawArrow(i: Int = 0) {
        val path = Path()

        val arrowStartXPosition = i * spacing.toPx()

        path.moveTo(x = arrowStartXPosition, y = 0F)

        val arrowEndXPosition = arrowWidth + (spacing.toPx() * i)

        if (arrowStartXPosition > size.width) return

        path.lineTo(x = arrowEndXPosition, y = size.height / 2)
        path.lineTo(x = i * spacing.toPx(), y = size.height)

        drawPath(
            path = path,
            color = if (arrowStartXPosition >= size.width * progress) unshadedColor else shadedColor,
            style = Stroke(
                width = arrowThickness.toPx(),
                cap = if (makeEndsSharp) StrokeCap.Square else StrokeCap.Round
            )
        )

        drawArrow(i + 1)
    }

    drawArrow()
}

@Preview
@Composable
fun ArrowHeadsProgressIndicatorPreview() {
    Canvas(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
            .height(20.dp)
    ) {
        ArrowHeadsProgressIndicator(
            inclinationInDegrees = 30F,
            progress = .5F,
            unshadedColor = Color.Green.copy(alpha = .3F),
            shadedColor = Color.Green,
            arrowThickness = 3.dp,
            spacing = 10.dp
        )
    }
}