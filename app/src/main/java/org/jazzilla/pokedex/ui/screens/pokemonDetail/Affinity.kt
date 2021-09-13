package org.jazzilla.pokedex.ui.screens.pokemonDetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jazzilla.pokedex.data.model.pokemon.PokemonType
import org.jazzilla.pokedex.data.model.pokemon.fakeBulbizarre
import org.jazzilla.pokedex.data.model.pokemon.AffinityType
import org.jazzilla.pokedex.data.model.pokemon.Pokemon
import org.jazzilla.pokedex.utils.functions.affinityImageIdForType
import org.jazzilla.pokedex.utils.functions.stringIdForPokemonType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonAffinity(pokemon: Pokemon) {
    LazyVerticalGrid(cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(bottom = 12.dp)
    ) {
        pokemon.mappedAffinities.forEach { affinity ->
            item {
                Affinity(type = affinity.key, has = affinity.value)
            }
        }
    }
}

@Composable
private fun Affinity(type: PokemonType, has: AffinityType) {
    val affinityImageId = affinityImageIdForType(type)
    val typeStringId = stringIdForPokemonType(type)

    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Image(painter = painterResource(id = affinityImageId),
            contentDescription = stringResource(id = typeStringId),
            modifier = Modifier
                .size(36.dp)
                .shadow(8.dp, shape = RoundedCornerShape(50))
        )

        val affinityValue = when(has) {
            AffinityType.DOUBLE_WEAKNESS -> "4"
            AffinityType.WEAKNESS -> "2"
            AffinityType.NORMAL -> "1"
            AffinityType.STRENGTH -> "\u00BD"
            AffinityType.DOUBLE_STRENGTH -> "\u00BC"
            AffinityType.IMMUNITY -> "0"
        }

        Text("x $affinityValue",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .padding(start = 12.dp)
                .width(32.dp)
        )
    }
}

@Composable
@Preview
private fun PokemonAffinity_Preview() {
    PokemonAffinity(pokemon = fakeBulbizarre)
}