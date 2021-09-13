package org.jazzilla.pokedex.data.model.pokemon

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey val number: Int,
    val name: String,
    val description: String,
    val height: Float,
    val weight: Float,
    val category: String,
    val mainType: PokemonType,
    val types: List<PokemonType>,
    @Embedded val gender: Gender,
    @Embedded val statistics: Statistics,
    val affinities: Affinity,
    @Embedded val breeding: Breeding,
    val evolutionChainId: Int) {

    val mappedAffinities: Map<PokemonType, AffinityType>
        get() = mapOf(
            PokemonType.NORMAL to affinities.normal,
            PokemonType.FIRE to affinities.fire,
            PokemonType.WATER to affinities.water,
            PokemonType.ELECTRIC to affinities.electric,
            PokemonType.GRASS to affinities.grass,
            PokemonType.ICE to affinities.ice,
            PokemonType.FIGHTING to affinities.fighting,
            PokemonType.POISON to affinities.poison,
            PokemonType.GROUND to affinities.ground,
            PokemonType.FLYING to affinities.flying,
            PokemonType.PSYCHIC to affinities.psychic,
            PokemonType.BUG to affinities.bug,
            PokemonType.ROCK to affinities.rock,
            PokemonType.GHOST to affinities.ghost,
            PokemonType.DRAGON to affinities.dragon,
            PokemonType.DARK to affinities.dark,
            PokemonType.STEEL to affinities.steel,
            PokemonType.FAIRY to affinities.fairy
        )
}

enum class PokemonType {
    GRASS,
    FIRE,
    WATER,
    POISON,
    NORMAL,
    ELECTRIC,
    ICE,
    FIGHTING,
    GROUND,
    FLYING,
    PSYCHIC,
    BUG,
    ROCK,
    GHOST,
    DRAGON,
    DARK,
    STEEL,
    FAIRY
}