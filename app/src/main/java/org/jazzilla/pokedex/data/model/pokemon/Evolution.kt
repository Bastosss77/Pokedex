package org.jazzilla.pokedex.data.model.pokemon

data class Evolution(val name: String, val id: Int, val to: List<Evolution>, val types: List<EvolutionType>)

sealed class EvolutionType(val value: String) {
    class LevelEvolution(level: Int) : EvolutionType(level.toString())
    class ItemEvolution(item: String) : EvolutionType(item)
}

enum class EvolutionTypeTypes {
    LEVEL, ITEM
}