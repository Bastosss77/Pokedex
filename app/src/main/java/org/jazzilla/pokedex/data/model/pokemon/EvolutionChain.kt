package org.jazzilla.pokedex.data.model.pokemon

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EvolutionChain(
    @PrimaryKey val id: Int,
    val basePokemonName: String,
    val basePokemonId: Int,
    val evolutions: List<Evolution>)