package org.jazzilla.pokedex.ui.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.lang.Math.max

@Composable
fun StaggeredGrid(content: @Composable () -> Unit) {
    Layout(content = content) { measurables, constraints ->
        val rowsHeight = mutableListOf<Int>()
        val maxWidth = constraints.maxWidth

        var tempRowWidth = 0
        var row = 0
        val placeables = measurables.map { measurable ->
            val placeable = measurable.measure(constraints)

            if((tempRowWidth + placeable.width) < maxWidth) {
                tempRowWidth += placeable.width

                if(rowsHeight.getOrNull(row) == null) {
                    rowsHeight.add(row, placeable.height)
                } else {
                    rowsHeight[row] = max(rowsHeight[row], placeable.height)
                }
            } else {
                row++
                tempRowWidth = placeable.width

                rowsHeight.add(row, placeable.height)
            }

            placeable
        }

        val rowY = IntArray(rowsHeight.size) { 0 }
        for(i in 1 until rowsHeight.size) {
            rowY[i] = rowY[i - 1] + rowsHeight[i - 1]
        }

        val layoutHeight = rowsHeight.sum()

        layout(constraints.maxWidth, layoutHeight) {
            var currentRow = 0
            var currentWidth = 0

            placeables.forEachIndexed { index, placeable ->
                if((currentWidth + placeable.width) < maxWidth) {
                    placeable.placeRelative(currentWidth, rowY[currentRow])
                    currentWidth += placeable.width
                } else {
                    currentRow++
                    currentWidth = 0

                    placeable.placeRelative(currentWidth, rowY[currentRow])
                    currentWidth += placeable.width
                }
            }
        }
    }
}

@Composable
@Preview
private fun StaggeredGrid_Preview() {
    val items = listOf("Fesse", "skjgnsd", "sdkjdsn", "aa", "sfgfdgdfhgdfh", "zqsfdqs", "dfskjdnj", "Fesse", "skjgnsd", "sdkjdsn", "aa", "sfgfdgdfhgdfh", "zqsfdqs", "dfskjdnj", "Fesse", "skjgnsd", "sdkjdsn", "aa", "sfgfdgdfhgdfh", "zqsfdqs", "dfskjdnj")

    StaggeredGrid {
        items.forEach { 
            Chip(text = it,
                backgroundColor = Color.Blue,
                modifier = Modifier.padding(4.dp))
        }
    }
}