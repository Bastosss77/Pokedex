package org.jazzilla.pokedex.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jazzilla.pokedex.data.model.pokemon.Stat

class StatConverter {

    @TypeConverter
    fun toStat(json: String) : Stat {
        val typeToken = object : TypeToken<Stat>() {}.type

        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun fromStat(stat: Stat) : String = Gson().toJson(stat)
}