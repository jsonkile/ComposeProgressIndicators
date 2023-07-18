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
 * @param progress the pill to shade down from
 * @param totalNumberOfPills the number of pills the indicator should have
 * @param shadedPillsColor the color of shaded pills
 * @param unshadedPillsColor the color of unshaded pills
 * @param useSharpEndedPills whether to use sharp pills instead of rounded ones
 * @param pillsSpacing specify the spaces between pills, defaults to 10dp
 */
fun DrawScope.PillsLinearProgressIndicator(
    progress: Int = 3,
    totalNumberOfPills: Int = 20,
    shadedPillsColor: Color = Color.Green,
    unshadedPillsColor: Color = Color.Green.copy(alpha = .3F),
    useSharpEndedPills: Boolean = true,
    pillsSpacing: Dp = 8.dp
) {
    val barArea = size.width / totalNumberOfPills
    val barLength = barArea - pillsSpacing.toPx()

    var nextBarStartPosition = 0F

    for (i in 0..totalNumberOfPills) {
        val barStartPosition = nextBarStartPosition + (pillsSpacing / 2).toPx()
        val barEndPosition = barStartPosition + barLength

        val start = Offset(x = barStartPosition, y = size.height / 2)
        val end = Offset(x = barEndPosition, y = size.height / 2)

        drawLine(
            cap = if (useSharpEndedPills) StrokeCap.Butt else StrokeCap.Round,
            color = if (i < progress) shadedPillsColor else unshadedPillsColor,
            start = start,
            end = end,
            strokeWidth = size.height
        )

        nextBarStartPosition = barEndPosition + (pillsSpacing / 2).toPx()
    }
}

@Preview
@Composable
internal fun PillsLinearProgressIndicatorPreview() {
    Canvas(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
            .height(5.dp)
    ) {
        PillsLinearProgressIndicator()
    }
}