package org.jazzilla.pokedex.ui.screens.pokemonList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.jazzilla.pokedex.R
import org.jazzilla.pokedex.data.model.SimplePokemon
import org.jazzilla.pokedex.ui.theme.whiteOverlay
import org.jazzilla.pokedex.ui.screens.pokemonList.viewmodel.PokedexViewModel
import org.jazzilla.pokedex.ui.widget.Chip
import org.jazzilla.pokedex.utils.functions.colorForPokemonType
import org.jazzilla.pokedex.utils.functions.formattedPokemonNumber
import org.jazzilla.pokedex.utils.functions.imageIdForPokemonNumber
import org.jazzilla.pokedex.utils.functions.stringIdForPokemonType

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun PokemonListScreen(navController: NavController) {
    val pokedexViewModel = hiltViewModel<PokedexViewModel>()
    val simplePokemonListState = pokedexViewModel.getAllSimplified().collectAsState(initial = listOf())

    val saveableList = rememberSaveable(simplePokemonListState.value) {
        mutableStateOf(simplePokemonListState.value)
    }

    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(saveableList.value) {
            PokedexCard(pokemon = it,
                modifier = Modifier
                    .clickable {
                        navController.navigate("pokemon/${it.number}")
                    }
                    .padding(4.dp))
        }
    }
}

@Composable
private fun PokedexCard(pokemon: SimplePokemon, modifier: Modifier = Modifier) {
    val background = colorForPokemonType(pokemon.mainType)
    
    ConstraintLayout(modifier = modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(background)
    ) {
        val (types, number, backgroundImage, pokemonImage) = createRefs()
        val backgroundImageSize = 100.dp
        val pokemonImageSize = 80.dp
        val backgroundImageOffset = backgroundImageSize.times(0.2f)

        Column(modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp, start = 8.dp)
            .constrainAs(types) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
        ) {
            Text(text = pokemon.name,
                color = Color.White,
                style = MaterialTheme.typography.h5)

            for(i in 0..2) {
                val type = pokemon.types.getOrNull(i)

                if(type != null) {
                    val typeStringId = stringIdForPokemonType(type)

                    Chip(text = stringResource(typeStringId),
                        backgroundColor = whiteOverlay,
                        textColor = Color.White,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                } else {
                    Chip(text = "",
                        backgroundColor = whiteOverlay,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .alpha(0f)
                    )
                }
            }
        }

        Text(text = "# ${formattedPokemonNumber(pokemon.number)}",
            color = Color.White,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .constrainAs(number) {
                    end.linkTo(parent.end, margin = 4.dp)
                    top.linkTo(parent.top, margin = 4.dp)
                }
        )

        Image(painter = painterResource(id = R.drawable.ic_pokeball),
            colorFilter = ColorFilter.tint(whiteOverlay),
            contentDescription = null,
            modifier = Modifier
                .size(backgroundImageSize)
                .constrainAs(backgroundImage) {
                    bottom.linkTo(types.bottom, margin = -backgroundImageOffset)
                    end.linkTo(parent.end, margin = -backgroundImageOffset)
                }
        )

        val imageId = imageIdForPokemonNumber(pokemon.number, LocalContext.current)

        if(imageId != null) {
            Image(painter = painterResource(id = imageId),
                contentDescription = pokemon.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(pokemonImageSize)
                    .constrainAs(pokemonImage) {
                        bottom.linkTo(types.bottom)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}

@Composable
@Preview
private fun PokedexScreen_Preview() {
    PokemonListScreen(NavController(LocalContext.current))
}

@Composable
@Preview
private fun PokedexCard_preview() {
    //PokedexCard(fakeBulbizarre)
}
