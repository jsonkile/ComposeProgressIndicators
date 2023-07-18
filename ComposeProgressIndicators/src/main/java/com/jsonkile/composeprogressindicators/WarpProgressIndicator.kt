package com.jsonkile.composeprogressindicators

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun DrawScope.WarpProgressIndicator(
    progress: Float = .56F,
    steps: Int,
    shadedPathColor: Color = Color.Green,
    unshadedPathColor: Color = Color.Green.copy(alpha = 0.3F),
    thickness: Dp = 2.dp
) {

    val gap = size.width / steps

    fun makePath(path: Path, x: Float = 0F, y: Float = size.height, end: Float = size.width) {
        if (x >= end - gap) return else {
            path.apply {

                cubicTo(
                    x1 = (x + x + gap) / 2,
                    y1 = if (y == 0F) size.height else 0F,
                    x2 = (x + x + gap) / 2,
                    y2 = y,
                    x3 = x + gap,
                    y3 = y
                )

            }

            makePath(path = path, x = x + gap, y = if (y == 0F) size.height else 0F, end = end)
        }
    }

    val backgroundPath = Path()
    makePath(path = backgroundPath)

    drawPath(
        path = backgroundPath,
        color = unshadedPathColor,
        style = Stroke(width = thickness.toPx())
    )

    val progressPath = Path()
    makePath(path = progressPath, end = size.width)

    clipRect(
        top = -10F,
        bottom = size.height + 10f,
        right = (size.width * progress),
        left = 0F,
        clipOp = ClipOp.Intersect
    ) {
        drawPath(
            path = progressPath,
            color = shadedPathColor,
            style = Stroke(width = thickness.toPx(), cap = StrokeCap.Round)
        )
    }
}

@Preview
@Composable
internal fun WarpProgressIndicatorPreview() {
    Canvas(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(10.dp)
    ) {
        WarpProgressIndicator(steps = 10)
    }

}