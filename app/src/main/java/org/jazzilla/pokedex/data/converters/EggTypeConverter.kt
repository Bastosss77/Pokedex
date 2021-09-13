package org.jazzilla.pokedex.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jazzilla.pokedex.data.model.pokemon.EggType

class EggTypeListConverter {

    @TypeConverter
    fun toEggTypeList(json: String) : List<EggType> {
        val typeToken = object : TypeToken<List<EggType>>() {}.type

        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun fromEggTypeList(list: List<EggType>) : String = Gson().toJson(list)
}