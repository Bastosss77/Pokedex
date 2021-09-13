package org.jazzilla.pokedex.ui.screens.pokemonDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jazzilla.pokedex.R
import org.jazzilla.pokedex.data.model.pokemon.*
import org.jazzilla.pokedex.ui.theme.*
import org.jazzilla.pokedex.utils.functions.imageIdForPokemonNumber

@Composable
fun PokemonEvolution(chain: EvolutionChain) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
    ) {
        if(chain.evolutions.isEmpty()) {
            Text(
                text = stringResource(R.string.pokemon_detail_no_evolution),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        } else {
            Text(stringResource(R.string.pokemon_detail_evolution_chain),
                style = MaterialTheme.typography.h4
            )

            Spacer(Modifier.height(12.dp))

            for(index in chain.evolutions.indices) {
                if(index == 0) {
                    Evolution(from = chain.basePokemonName, fromId = chain.basePokemonId, to = chain.evolutions[index])
                } else {
                    Evolution(from = chain.evolutions[index - 1].name,
                        fromId = chain.evolutions[index - 1].id,
                        to = chain.evolutions[index]
                    )
                }

                if(index != chain.evolutions.size - 1 ) {
                    Divider(color = divider,
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun Evolution(from: String, fromId: Int, to: Evolution) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        EvolutionImage(name = from, id = fromId)
        EvolutionType(type = to.types.first())
        EvolutionImage(name = to.name, id = to.id)
    }
}

@Composable
private fun EvolutionImage(name: String, id: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.ic_pokeball),
                contentDescription = name,
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(grayOverlay),
                modifier = Modifier
                    .size(70.dp)
            )

            val imageId = imageIdForPokemonNumber(id, LocalContext.current)

            if(imageId != null) {
                Image(painter = painterResource(id = imageId),
                    contentDescription = name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        }

        Text(name, style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
private fun EvolutionType(type: EvolutionType) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = Icons.Rounded.ArrowForward,
            contentDescription = null,
            modifier = Modifier.size(30.dp), tint = gray200
        )

        val text: String = when(type) {
            is EvolutionType.LevelEvolution -> {
                stringResource(id = R.string.pokemon_detail_level, type.value.toInt())
            }
            is EvolutionType.ItemEvolution -> {
                type.value
            }
        }

        Text(text = text, style = MaterialTheme.typography.body1)
    }
}

@Composable
@Preview
private fun PokemonEvolution_Preview() {
    val ev2 = Evolution(name = "Florizarre",
        id = 3,
        to = listOf(),
        types = listOf(EvolutionType.LevelEvolution(32))
    )

    val ev = Evolution(name = "Herbizarre",
        id = 2,
        to = listOf(ev2),
        types = listOf(EvolutionType.LevelEvolution(16))
    )

    val chain = EvolutionChain(1, "Bulbizarre", 1, listOf(ev))

    PokemonEvolution(chain = chain)
}