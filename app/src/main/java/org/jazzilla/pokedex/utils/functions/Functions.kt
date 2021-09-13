package org.jazzilla.pokedex.utils.functions

import android.content.Context
import androidx.compose.ui.graphics.Color
import org.jazzilla.pokedex.R
import org.jazzilla.pokedex.data.model.pokemon.EggType
import org.jazzilla.pokedex.data.model.pokemon.PokemonType
import org.jazzilla.pokedex.ui.theme.*

fun colorForPokemonType(type: PokemonType): Color =
    when(type) {
        PokemonType.GRASS -> plantType
        PokemonType.FIRE -> fireType
        PokemonType.WATER -> waterType
        PokemonType.POISON -> poisonType
        PokemonType.NORMAL -> normalType
        PokemonType.ELECTRIC -> electricType
        PokemonType.ICE -> iceType
        PokemonType.FIGHTING -> fightingType
        PokemonType.GROUND -> groundType
        PokemonType.FLYING, -> flyingType
        PokemonType.PSYCHIC -> psychicType
        PokemonType.BUG -> bugType
        PokemonType.ROCK -> rockType
        PokemonType.GHOST -> ghostType
        PokemonType.DRAGON -> dragonType
        PokemonType.DARK -> darkType
        PokemonType.STEEL -> steelType
        PokemonType.FAIRY -> fairyType
    }

fun imageIdForPokemonNumber(number: Int, context: Context): Int? {
    val imageName = "pokemon_$number"
    val imageId = context.resources.getIdentifier(imageName, "drawable", context.packageName)

    return if(imageId > 0) {
        imageId
    } else {
        null
    }
}

fun stringIdForPokemonType(type: PokemonType): Int =
    when(type) {
        PokemonType.GRASS -> R.string.pokemon_type_grass
        PokemonType.FIRE -> R.string.pokemon_type_fire
        PokemonType.WATER -> R.string.pokemon_type_water
        PokemonType.POISON -> R.string.pokemon_type_poison
        PokemonType.NORMAL -> R.string.pokemon_type_normal
        PokemonType.ELECTRIC -> R.string.pokemon_type_electric
        PokemonType.ICE -> R.string.pokemon_type_ice
        PokemonType.FIGHTING -> R.string.pokemon_type_fighting
        PokemonType.GROUND -> R.string.pokemon_type_ground
        PokemonType.FLYING, -> R.string.pokemon_type_flying
        PokemonType.PSYCHIC -> R.string.pokemon_type_psychic
        PokemonType.BUG -> R.string.pokemon_type_bug
        PokemonType.ROCK -> R.string.pokemon_type_rock
        PokemonType.GHOST -> R.string.pokemon_type_ghost
        PokemonType.DRAGON -> R.string.pokemon_type_dragon
        PokemonType.DARK -> R.string.pokemon_type_dark
        PokemonType.STEEL -> R.string.pokemon_type_steel
        PokemonType.FAIRY -> R.string.pokemon_type_fairy
}

fun affinityImageIdForType(type: PokemonType): Int =
    when(type) {
        PokemonType.GRASS -> R.drawable.ic_pokemon_type_grass
        PokemonType.FIRE -> R.drawable.ic_pokemon_type_fire
        PokemonType.WATER -> R.drawable.ic_pokemon_type_water
        PokemonType.POISON -> R.drawable.ic_pokemon_type_poison
        PokemonType.NORMAL -> R.drawable.ic_pokemon_type_normal
        PokemonType.ELECTRIC -> R.drawable.ic_pokemon_type_electric
        PokemonType.ICE -> R.drawable.ic_pokemon_type_ice
        PokemonType.FIGHTING -> R.drawable.ic_pokemon_type_fighting
        PokemonType.GROUND -> R.drawable.ic_pokemon_type_ground
        PokemonType.FLYING, -> R.drawable.ic_pokemon_type_flying
        PokemonType.PSYCHIC -> R.drawable.ic_pokemon_type_psychic
        PokemonType.BUG -> R.drawable.ic_pokemon_type_bug
        PokemonType.ROCK -> R.drawable.ic_pokemon_type_rock
        PokemonType.GHOST -> R.drawable.ic_pokemon_type_ghost
        PokemonType.DRAGON -> R.drawable.ic_pokemon_type_dragon
        PokemonType.DARK -> R.drawable.ic_pokemon_type_dark
        PokemonType.STEEL -> R.drawable.ic_pokemon_type_steel
        PokemonType.FAIRY -> R.drawable.ic_pokemon_type_fairy
    }

fun stringIdForEggType(type: EggType): Int =
    when(type) {
        EggType.AMORPHOUS -> R.string.pokemon_egg_type_amorphous
        EggType.BUG -> R.string.pokemon_egg_type_bug
        EggType.DRAGON -> R.string.pokemon_egg_type_dragon
        EggType.FAIRY -> R.string.pokemon_egg_type_fairy
        EggType.FIELD -> R.string.pokemon_egg_type_field
        EggType.FLYING -> R.string.pokemon_egg_type_flying
        EggType.PLANT -> R.string.pokemon_egg_type_grass
        EggType.HUMAN_LIKE -> R.string.pokemon_egg_type_human_like
        EggType.MINERAL -> R.string.pokemon_egg_type_mineral
        EggType.MONSTER -> R.string.pokemon_egg_type_monster
        EggType.WATER_1 -> R.string.pokemon_egg_type_water_1
        EggType.WATER_2 -> R.string.pokemon_egg_type_water_2
        EggType.WATER_3 -> R.string.pokemon_egg_type_water_3
        EggType.DITTO -> R.string.pokemon_egg_type_ditto
        EggType.UNKNOWN -> R.string.pokemon_egg_type_unknown
    }

fun formattedPokemonNumber(number: Int) : String {
    val zeroCount: Int = when {
        number > 100 -> 0
        number > 10 -> 1
        else -> 2
    }

    return "%0${zeroCount + 1}d".format(number)
}
