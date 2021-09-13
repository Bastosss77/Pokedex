package org.jazzilla.pokedex.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonArray
import org.jazzilla.pokedex.data.model.pokemon.Evolution

class EvolutionListConverter {

    @TypeConverter
    fun toEvolutionList(json: String) : List<Evolution> {
        val converter = EvolutionConverter()
        val jsonArray = Gson().fromJson(json, JsonArray::class.java)
        val evolutions = mutableListOf<Evolution>()

        jsonArray.forEach { element ->
            evolutions.add(converter.toEvolution(element.toString()))
        }

        return evolutions
    }

    @TypeConverter
    fun fromEvolutionList(list: List<Evolution>) : String {
        val converter = EvolutionConverter()
        var jsonArray = "["

        list.forEach { evolution ->
            jsonArray += converter.fromEvolution(evolution)
            jsonArray += ","
        }

        jsonArray = jsonArray.dropLast(1)
        jsonArray += "]"

        return jsonArray
    }
}