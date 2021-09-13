package org.jazzilla.pokedex.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jazzilla.pokedex.data.model.pokemon.Affinity

class AffinityConverter {

    @TypeConverter
    fun toAffinity(json: String) : Affinity {
        val typeToken = object : TypeToken<Affinity>() {}.type

        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun fromAffinity(affinity: Affinity) : String = Gson().toJson(affinity)
}