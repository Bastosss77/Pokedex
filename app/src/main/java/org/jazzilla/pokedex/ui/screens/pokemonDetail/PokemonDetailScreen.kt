package org.jazzilla.pokedex.ui.screens.pokemonDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import org.jazzilla.pokedex.utils.functions.colorForPokemonType
import org.jazzilla.pokedex.utils.functions.imageIdForPokemonNumber
import org.jazzilla.pokedex.R
import org.jazzilla.pokedex.data.model.pokemon.EvolutionChain
import org.jazzilla.pokedex.data.model.pokemon.Pokemon
import org.jazzilla.pokedex.data.model.pokemon.fakeBulbizarre
import org.jazzilla.pokedex.ui.screens.pokemonDetail.viewmodel.PokemonDetailViewModel
import org.jazzilla.pokedex.ui.theme.whiteOverlay
import org.jazzilla.pokedex.utils.functions.formattedPokemonNumber

private val horizontalPadding = 8.dp

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PokemonDetailScreen(navController: NavController, id: Int) {
    val pokemonDetailViewModel = hiltViewModel<PokemonDetailViewModel>()
    val pokemon = pokemonDetailViewModel.getOne(id).collectAsState(initial = null)

    pokemon.value?.let {
        Screen(navController = navController,
            pokemon = it.pokemon,
            evolutionChain = it.chain)
    }
}

@ExperimentalPagerApi
@Composable
private fun Screen(navController: NavController, pokemon: Pokemon, evolutionChain: EvolutionChain) {
    val mainColor = colorForPokemonType(pokemon.mainType)

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(mainColor)
    ) {
        NavBar(navController = navController)
        PokemonName(name = pokemon.name, number = pokemon.number)
        PokemonImage(fromNumber = pokemon.number)

        Column(modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(Color.White)
            .padding(top = 20.dp, start = 16.dp, end = 16.dp)
        ) {
            val tabs = listOf<Pair<String, @Composable () -> Unit >>(
                Pair(stringResource(R.string.pokemon_detail_about), { PokemonAbout(pokemon = pokemon) }),
                Pair(stringResource(R.string.pokemon_detail_stats), { PokemonStats(pokemon = pokemon) }),
                Pair(stringResource(R.string.pokemon_detail_evolution), { PokemonEvolution(chain = evolutionChain) }),
                Pair(stringResource(R.string.pokemon_detail_affinity), { PokemonAffinity(pokemon = pokemon) })
            )

            val pagerState = rememberPagerState(pageCount = 4)

            PokemonCategoriesTabs(tabs = tabs,
                pagerState = pagerState,
                backgroundColor = Color.White,
                indicatorColor = colorForPokemonType(pokemon.mainType)
            )

            PokemonCategoriesTabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}

@Composable
private fun NavBar(navController: NavController) {
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 12.dp, start = horizontalPadding, end = horizontalPadding)
            .fillMaxWidth()
    ) {
        Icon(imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier.clickable { navController.popBackStack() })
        Icon(imageVector = Icons.Rounded.FavoriteBorder,
            tint = Color.White,
            contentDescription = "Favorite")
    }
}

@Composable
private fun PokemonName(name: String, number: Int) {
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 16.dp, start = horizontalPadding, end = horizontalPadding)
            .fillMaxWidth()) {
        Text(text = name, style = MaterialTheme.typography.h2, color = Color.White)
        Text(text = "# ${formattedPokemonNumber(number)}", style = MaterialTheme.typography.subtitle1, color = Color.White)
    }
}

@Composable
private fun PokemonImage(fromNumber: Int) {
    Box {
        Image(painter = painterResource(id = R.drawable.ic_pokeball),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(whiteOverlay),
            modifier = Modifier
                .size(200.dp)
        )

        val imageId = imageIdForPokemonNumber(fromNumber, LocalContext.current)

        if(imageId != null) {
            Image(painter = painterResource(id = imageId),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(200.dp)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun PokemonCategoriesTabs(tabs: List<Pair<String, @Composable () -> Unit>>,
                                  pagerState: PagerState,
                                  backgroundColor: Color,
                                  indicatorColor: Color) {
    val scope = rememberCoroutineScope()

    ScrollableTabRow(selectedTabIndex = pagerState.currentPage,
        backgroundColor = backgroundColor,
        indicator = { tabsPosition ->
        TabRowDefaults.Indicator(
            color = indicatorColor,
            modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabsPosition)
        )
    }) {
        tabs.forEachIndexed { index, pair ->
            Tab(selected = pagerState.currentPage == index,
                text = {
                    Text(pair.first, style = MaterialTheme.typography.subtitle2)
                       },
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }

                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun PokemonCategoriesTabsContent(tabs: List<Pair<String, @Composable () -> Unit>>, pagerState: PagerState) {
    HorizontalPager(verticalAlignment = Alignment.Top,
        state = pagerState) { page ->
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(24.dp))
            tabs[page].second()
        }
    }
}


/***************************************** Previews *****************************************/


@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview
private fun PokemonScreen_Preview() {
    Screen(navController = NavController(LocalContext.current), pokemon = fakeBulbizarre, evolutionChain = EvolutionChain(1, "Bulbizarre", 1, listOf()))
}

@Composable
@Preview(name = "NavBar")
private fun NavBar_Preview() {
    NavBar(navController = NavController(LocalContext.current))
}

@Composable
@Preview(name = "PokemonName")
private fun PokemonName_Preview() {
    PokemonName(name = "Bulbizarre", number = 1)
}

@Composable
@Preview(name = "PokemonImage")
private fun PokemonImage_Preview() {
    PokemonImage(fromNumber = 1)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview(name = "PokemonCategoriesTabs")
private fun PokemonCategoriesTabs_Preview() {
    val tabs = listOf<Pair<String, @Composable () -> Unit >>(
        Pair(stringResource(R.string.pokemon_detail_about), { PokemonAbout(pokemon = fakeBulbizarre) }),
        Pair(stringResource(R.string.pokemon_detail_stats), { PokemonStats(pokemon = fakeBulbizarre) }),
        Pair(stringResource(R.string.pokemon_detail_evolution), { PokemonEvolution(chain = EvolutionChain(1, "fesse", 2, emptyList())) }),
        Pair(stringResource(R.string.pokemon_detail_affinity), { PokemonAffinity(pokemon = fakeBulbizarre) })
    )

    val pagerState = rememberPagerState(pageCount = tabs.size)
    PokemonCategoriesTabs(tabs = tabs,
        pagerState = pagerState,
        backgroundColor = Color.White,
        indicatorColor = colorForPokemonType(fakeBulbizarre.mainType)
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview(name = "PokemonCategoriesTabsContent")
private fun PokemonCategoriesTabsContent_Preview() {
    val tabs = listOf<Pair<String, @Composable () -> Unit >>(
        Pair(stringResource(R.string.pokemon_detail_about), { PokemonAbout(pokemon = fakeBulbizarre) }),
        Pair(stringResource(R.string.pokemon_detail_stats), { PokemonStats(pokemon = fakeBulbizarre) }),
        Pair(stringResource(R.string.pokemon_detail_evolution), { PokemonEvolution(chain = EvolutionChain(1, "fesse", 2, emptyList())) }),
        Pair(stringResource(R.string.pokemon_detail_affinity), { PokemonAffinity(pokemon = fakeBulbizarre) })
    )

    val pagerState = rememberPagerState(pageCount = tabs.size)
    PokemonCategoriesTabsContent(tabs = tabs, pagerState = pagerState)
}