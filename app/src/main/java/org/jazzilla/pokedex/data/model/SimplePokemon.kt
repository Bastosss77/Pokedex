package org.jazzilla.pokedex.data.model

import androidx.room.ColumnInfo
import org.jazzilla.pokedex.data.model.pokemon.PokemonType

data class SimplePokemon(
    @ColumnInfo(name = "number") val number: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "mainType") val mainType: PokemonType,
    @ColumnInfo(name = "types") val types: List<PokemonType>
    )