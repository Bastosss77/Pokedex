package org.jazzilla.pokedex.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jazzilla.pokedex.R

@Composable
fun Chip(text: String,
         backgroundColor: Color,
         modifier: Modifier = Modifier,
         imagePainter: Painter? = null,
         textColor: Color = Color.Black,
         textStyle: TextStyle = MaterialTheme.typography.body1) {

    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(backgroundColor)
    ) {

        if(imagePainter != null) {
            Image(painter = imagePainter,
                contentDescription = null,
                modifier = Modifier.padding(start = 8.dp))
        }

        Text(text = text,
            color = textColor,
            style = textStyle,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
    }
}

@Composable
@Preview
private fun Chip_preview() {
    Chip(text = "Grass", backgroundColor = Color.Red, imagePainter = painterResource(id = R.drawable.ic_pokemon_type_fairy))
}