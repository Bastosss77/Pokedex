package org.jazzilla.pokedex.data.model.pokemon

data class Affinity(
    val normal: AffinityType,
    val fire: AffinityType,
    val water: AffinityType,
    val electric: AffinityType,
    val grass: AffinityType,
    val ice: AffinityType,
    val fighting: AffinityType,
    val poison: AffinityType,
    val ground: AffinityType,
    val flying: AffinityType,
    val psychic: AffinityType,
    val bug: AffinityType,
    val rock: AffinityType,
    val ghost: AffinityType,
    val dragon: AffinityType,
    val dark: AffinityType,
    val steel: AffinityType,
    val fairy: AffinityType
)

enum class AffinityType {
    IMMUNITY, DOUBLE_STRENGTH, STRENGTH, NORMAL, WEAKNESS, DOUBLE_WEAKNESS
}
