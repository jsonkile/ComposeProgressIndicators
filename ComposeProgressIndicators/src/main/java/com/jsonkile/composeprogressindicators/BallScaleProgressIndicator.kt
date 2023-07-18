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


fun DrawScope.BallScaleProgressIndicator(
    shadedAreaColor: Color = Color.Green,
    unShadedAreaColor: Color = Color.Green.copy(alpha = 0.3F),
    progress: Float = .3F,
    makeLineEndsFlat: Boolean = false,
    radius: Dp = 10.dp,
    lineThickness: Dp = 4.dp,
) {
    drawLine(
        start = Offset(x = 0F, y = size.height / 2),
        end = Offset(x = size.width * progress, y = size.height / 2),
        strokeWidth = lineThickness.toPx(),
        color = shadedAreaColor,
        cap = if (makeLineEndsFlat) StrokeCap.Butt else StrokeCap.Round
    )

    drawCircle(
        center = Offset(x = size.width * progress, y = size.height / 2),
        color = shadedAreaColor,
        radius = radius.toPx()
    )

    drawLine(
        start = Offset(x = size.width * progress, y = size.height / 2),
        end = Offset(x = size.width, y = size.height / 2),
        strokeWidth = lineThickness.toPx(),
        color = unShadedAreaColor,
        cap = if (makeLineEndsFlat) StrokeCap.Butt else StrokeCap.Round
    )
}

@Preview
@Composable
internal fun BallScaleProgressIndicatorPreview() {
    Canvas(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(20.dp)
    ) {
        BallScaleProgressIndicator()
    }
}