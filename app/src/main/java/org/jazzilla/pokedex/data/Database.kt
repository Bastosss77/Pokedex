package org.jazzilla.pokedex.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.jazzilla.pokedex.data.converters.*
import org.jazzilla.pokedex.data.dao.PokemonDao
import org.jazzilla.pokedex.data.model.pokemon.EvolutionChain
import org.jazzilla.pokedex.data.model.pokemon.Pokemon

@Database(entities = [Pokemon::class, EvolutionChain::class], version = 1)
@TypeConverters(PokemonTypeListConverter::class,
    StatConverter::class,
    AffinityConverter::class,
    EggTypeListConverter::class,
    EvolutionConverter::class,
    EvolutionListConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao() : PokemonDao
}