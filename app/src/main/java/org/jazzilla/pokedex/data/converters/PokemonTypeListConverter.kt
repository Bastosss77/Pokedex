package org.jazzilla.pokedex.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jazzilla.pokedex.data.model.pokemon.PokemonType

class PokemonTypeListConverter {

    @TypeConverter
    fun toPokemonTypeList(json: String): List<PokemonType> {
        val typeToken = object : TypeToken<List<PokemonType>>() {}.type

        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun fromPokemonTypeList(types: List<PokemonType>) : String = Gson().toJson(types)
}