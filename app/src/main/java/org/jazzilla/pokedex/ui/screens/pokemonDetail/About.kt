package org.jazzilla.pokedex.ui.screens.pokemonDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Female
import androidx.compose.material.icons.rounded.Male
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jazzilla.pokedex.R
import org.jazzilla.pokedex.data.model.pokemon.Pokemon
import org.jazzilla.pokedex.data.model.pokemon.fakeBulbizarre
import org.jazzilla.pokedex.ui.theme.femaleGenderColor
import org.jazzilla.pokedex.ui.theme.grayText
import org.jazzilla.pokedex.ui.theme.maleGenderColor
import org.jazzilla.pokedex.ui.widget.Chip
import org.jazzilla.pokedex.utils.Unit
import org.jazzilla.pokedex.utils.functions.colorForPokemonType
import org.jazzilla.pokedex.utils.functions.stringIdForEggType
import org.jazzilla.pokedex.utils.functions.stringIdForPokemonType

@Composable
fun PokemonAbout(pokemon: Pokemon) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(text = pokemon.description, style = MaterialTheme.typography.body1)

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(24.dp))

        Card(shape = RoundedCornerShape(12.dp),
            backgroundColor = Color.White,
            elevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Row(horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Column(modifier = Modifier.wrapContentSize()) {
                    Text(stringResource(R.string.pokemon_detail_height), style = MaterialTheme.typography.subtitle2, color = grayText)
                    Text("${pokemon.height} ${Unit.centimeter}", style = MaterialTheme.typography.subtitle1)
                }

                Column(modifier = Modifier.wrapContentSize()) {
                    Text(stringResource(R.string.pokemon_detail_weight), style = MaterialTheme.typography.subtitle2, color = grayText)
                    Text("${pokemon.weight} ${Unit.kilogram}", style = MaterialTheme.typography.subtitle1)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(stringResource(R.string.pokemon_detail_infos), style = MaterialTheme.typography.h4)

        val rowTextWidth = 80.dp
        Column(verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(R.string.pokemon_detail_category),
                    style = MaterialTheme.typography.subtitle2,
                    color = grayText,
                    modifier = Modifier.width(rowTextWidth)
                )

                Text(pokemon.category,
                    style = MaterialTheme.typography.subtitle2
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    stringResource(R.string.pokemon_detail_types),
                    style = MaterialTheme.typography.subtitle2,
                    color = grayText,
                    modifier = Modifier.width(rowTextWidth)
                )

                pokemon.types.forEach { type ->
                    val typeStringId = stringIdForPokemonType(type)
                    val color = colorForPokemonType(type)

                    Chip(text = stringResource(typeStringId),
                        backgroundColor = color,
                        textColor = Color.White
                    )

                    Spacer(modifier = Modifier.padding(end = 4.dp))
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    stringResource(R.string.pokemon_detail_sex),
                    style = MaterialTheme.typography.subtitle2,
                    color = grayText,
                    modifier = Modifier.width(rowTextWidth)
                )

                Icon(imageVector = Icons.Rounded.Female,
                    contentDescription = "Female",
                    tint = femaleGenderColor
                )

                Text("${pokemon.gender.female}%", style = MaterialTheme.typography.subtitle1)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(imageVector = Icons.Rounded.Male,
                    contentDescription = "Male",
                    tint = maleGenderColor
                )

                Text("${pokemon.gender.male}%", style = MaterialTheme.typography.subtitle1)
            }
        }

        Text(stringResource(R.string.pokemon_detail_breeding), style = MaterialTheme.typography.h4)
        Column(verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(R.string.pokemon_detail_breeding_eggs),
                    style = MaterialTheme.typography.subtitle2,
                    color = grayText,
                    modifier = Modifier.width(rowTextWidth)
                )

                pokemon.breeding.eggTypes.forEach { eggType ->
                    val typeStringId = stringIdForEggType(eggType)

                    Chip(text = stringResource(id = typeStringId),
                        backgroundColor = Color.Gray,
                        textColor = Color.White
                    )

                    Spacer(modifier = Modifier.padding(end = 4.dp))
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(R.string.pokemon_detail_breeding_hatching),
                    style = MaterialTheme.typography.subtitle2,
                    color = grayText,
                    modifier = Modifier.width(rowTextWidth)
                )

                //Don't know why I can't put arguments in
                val cycles = LocalContext.current.resources.getQuantityString(R.plurals.pokemon_detail_breeding_cycle, pokemon.breeding.cyclesToHatch)
                val steps = stringResource(id = R.string.pokemon_detail_breeding_steps, pokemon.breeding.stepsToHatch)
                Text(text = "${pokemon.breeding.cyclesToHatch} $cycles - $steps",
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}

@Composable
@Preview
private fun PokemonAbout_Preview() {
    PokemonAbout(pokemon = fakeBulbizarre)
}
