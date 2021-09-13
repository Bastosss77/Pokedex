package org.jazzilla.pokedex.ui.screens.pokemonDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jazzilla.pokedex.R
import org.jazzilla.pokedex.data.model.pokemon.Pokemon
import org.jazzilla.pokedex.data.model.pokemon.fakeBulbizarre
import org.jazzilla.pokedex.data.model.pokemon.Statistics
import org.jazzilla.pokedex.ui.theme.gray300
import org.jazzilla.pokedex.ui.theme.grayText
import org.jazzilla.pokedex.utils.functions.colorForPokemonType

@Composable
fun PokemonStats(pokemon: Pokemon) {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())) {
        Text(
            stringResource(id = R.string.pokemon_stats_title),
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            val arrangement = spacedBy(8.dp)
            //Not the best way but it's simpler to align all elements
            StatsNames(verticalArrangement = arrangement)
            Spacer(modifier = Modifier.width(12.dp))
            StatsValues(verticalArrangement = arrangement,stats = pokemon.statistics)
            StatsProgress(verticalArrangement = arrangement, stats = pokemon.statistics, barColor = colorForPokemonType(pokemon.mainType))
            StatsMin(verticalArrangement = arrangement, stats = pokemon.statistics)
            Spacer(modifier = Modifier.width(12.dp))
            StatsMax(verticalArrangement = arrangement, stats = pokemon.statistics)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(stringResource(id = R.string.pokemon_stat_indication),
            style = MaterialTheme.typography.body1)
    }
}

@Composable
private fun StatsNames(modifier: Modifier = Modifier, verticalArrangement: Arrangement.Vertical) {
    val allStats = listOf(
        R.string.pokemon_stat_hp,
        R.string.pokemon_stat_attack,
        R.string.pokemon_stat_defense,
        R.string.pokemon_stat_special_attack,
        R.string.pokemon_stat_defense,
        R.string.pokemon_stat_speed,
        R.string.pokemon_stat_total
    )

    Column(verticalArrangement = verticalArrangement,
        modifier = modifier){
        allStats.forEach { statResId ->
            Text(text = stringResource(id = statResId),
                color = grayText,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.height(20.dp)
            )
        }
    }
}

@Composable
private fun StatsValues(stats: Statistics, modifier: Modifier = Modifier, verticalArrangement: Arrangement.Vertical) {
    val allStatsValue = listOf(
        stats.hp.base,
        stats.attack.base,
        stats.defense.base,
        stats.specialAttack.base,
        stats.specialDefense.base,
        stats.speed.base,
        stats.totalBase
    )

    Column(verticalArrangement = verticalArrangement,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier) {
        allStatsValue.forEach { value ->
            Text("$value",
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.height(20.dp)
            )
        }
    }
}

@Composable
private fun StatsProgress(stats: Statistics, barColor: Color, verticalArrangement: Arrangement.Vertical, modifier: Modifier = Modifier) {
    val allStatsValue = listOf(
        stats.hp.base,
        stats.attack.base,
        stats.defense.base,
        stats.specialAttack.base,
        stats.specialDefense.base,
        stats.speed.base
    )

    Column(verticalArrangement = verticalArrangement,
        modifier = modifier) {
        allStatsValue.forEach { value ->
            val progress = value.toFloat() / 100f

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.height(20.dp)
            ) {
                LinearProgressIndicator(progress = progress,
                    color = barColor,
                    backgroundColor = gray300,
                    modifier = Modifier
                        .width(150.dp)
                        .padding(start = 12.dp, end = 12.dp)
                        .clip(RoundedCornerShape(50))
                )
            }
        }
    }
}

@Composable
private fun StatsMin(stats: Statistics, verticalArrangement: Arrangement.Vertical, modifier: Modifier = Modifier) {
    val allMinStats = listOf(
        stats.hp.min,
        stats.attack.min,
        stats.defense.min,
        stats.specialAttack.min,
        stats.specialDefense.min,
        stats.speed.min
    )

    Column(verticalArrangement = verticalArrangement,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.wrapContentWidth()) {
        allMinStats.forEach { value ->
            Text("$value",
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.height(20.dp)
            )
        }

        Text(
            stringResource(id = R.string.pokemon_stat_min),
            color = grayText,
            style = MaterialTheme.typography.subtitle2)
    }
}

@Composable
private fun StatsMax(stats: Statistics, verticalArrangement: Arrangement.Vertical, modifier: Modifier = Modifier) {
    val allMinStats = listOf(
        stats.hp.max,
        stats.attack.max,
        stats.defense.max,
        stats.specialAttack.max,
        stats.specialDefense.max,
        stats.speed.max
    )

    Column(verticalArrangement = verticalArrangement,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier) {
        allMinStats.forEach { value ->
            Text("$value",
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.height(20.dp)
            )
        }

        Text(
            stringResource(id = R.string.pokemon_stat_max),
            color = grayText,
            style = MaterialTheme.typography.subtitle2)
    }
}

@Composable
@Preview
private fun PokemonStats_Preview() {
    PokemonStats(pokemon = fakeBulbizarre)
}