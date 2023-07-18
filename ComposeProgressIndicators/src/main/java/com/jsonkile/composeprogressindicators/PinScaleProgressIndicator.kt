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
 * @param barColor the color of shaded pills
 */
fun DrawScope.PinScaleProgressIndicator(
    progress: Float = 0.35F,
    barColor: Color = Color.LightGray,
    barThickness: Dp = 2.dp,
    pointerColor: Color = Color.LightGray,
    pointerThickness: Dp = 2.dp,
    pointerHeight: Dp = 15.dp,
    makeLineEndsFlat: Boolean = false,
) {
    drawLine(
        start = Offset(x = 0F, y = size.height / 2),
        end = Offset(x = size.width, y = size.height / 2),
        color = barColor,
        strokeWidth = barThickness.toPx(),
        cap = if (makeLineEndsFlat.not()) StrokeCap.Round else StrokeCap.Butt
    )

    drawLine(
        start = Offset(
            x = size.width * progress,
            y = (size.height / 2) - (pointerHeight.toPx() / 2)
        ),
        end = Offset(x = size.width * progress, y = (size.height / 2) + (pointerHeight.toPx() / 2)),
        color = pointerColor,
        strokeWidth = pointerThickness.toPx(),
        cap = if (makeLineEndsFlat.not()) StrokeCap.Round else StrokeCap.Butt
    )
}

@Preview
@Composable
internal fun PinScaleProgressIndicatorPreview() {
    Canvas(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(45.dp)
    ) {
        PinScaleProgressIndicator()
    }
}