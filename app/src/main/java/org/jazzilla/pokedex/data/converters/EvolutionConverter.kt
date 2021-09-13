package org.jazzilla.pokedex.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.jazzilla.pokedex.data.model.pokemon.Evolution
import org.jazzilla.pokedex.data.model.pokemon.EvolutionType
import org.jazzilla.pokedex.data.model.pokemon.EvolutionTypeTypes

class EvolutionConverter {
/*

   {
      "name":"Herbizarre",
      "id":2,
      "to":[
         {
            "name":"Florizarre",
            "id":3,
            "to":[

            ],
            "types":[
               {
                  "type":"LEVEL",
                  "value":32
               }
            ]
         }
      ],
      "types":[
         {
            "type":"LEVEL",
            "value":16
         }
      ]
   }

 */


    @TypeConverter
    fun toEvolution(json: String) : Evolution {
        val jsonObject = Gson().fromJson(json, JsonObject::class.java)

        val obj = parseEvolution(jsonObject)

        return obj
    }

    @TypeConverter
    fun fromEvolution(evolution: Evolution) : String {
        return ""
    }

    private fun parseEvolution(from: JsonObject) : Evolution {
        val name = from[Evolution::name.name].asString
        val id = from[Evolution::id.name].asInt
        val jsonTypes = from[Evolution::types.name].asJsonArray
        val types = parseEvolutionTypes(jsonTypes)
        val evolutions = from[Evolution::to.name].asJsonArray.map { parseEvolution(it.asJsonObject) }

        return Evolution(name, id, evolutions, types)
    }

    private fun parseEvolutionTypes(array: JsonArray): List<EvolutionType> {
        return array.map { element ->
            val typeObject = element.asJsonObject

            return@map when (typeObject["type"].asString) {
                EvolutionTypeTypes.LEVEL.name -> buildLevelEvolution(typeObject)
                EvolutionTypeTypes.ITEM.name -> buildItemEvolution(typeObject)
                else -> throw Exception("Not match for ${typeObject["type"].asString} for evolution type")
            }
        }
    }

    private fun buildLevelEvolution(from: JsonObject) : EvolutionType.LevelEvolution =
        EvolutionType.LevelEvolution(from[EvolutionType.LevelEvolution::value.name].asInt)

    private fun buildItemEvolution(from: JsonObject) : EvolutionType.ItemEvolution =
        EvolutionType.ItemEvolution(from[EvolutionType.ItemEvolution::value.name].asString)

}