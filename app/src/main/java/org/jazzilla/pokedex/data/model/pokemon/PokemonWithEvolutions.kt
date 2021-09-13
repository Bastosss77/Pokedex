package org.jazzilla.pokedex.data.model.pokemon

import androidx.room.Embedded
import androidx.room.Relation

data class PokemonWithEvolutions(
    @Embedded val pokemon: Pokemon,
    @Relation(parentColumn = "evolutionChainId", entityColumn = "id") val chain: EvolutionChain
)