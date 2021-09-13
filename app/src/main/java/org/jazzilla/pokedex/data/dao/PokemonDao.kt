package org.jazzilla.pokedex.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import org.jazzilla.pokedex.data.model.SimplePokemon
import org.jazzilla.pokedex.data.model.pokemon.Pokemon
import org.jazzilla.pokedex.data.model.pokemon.PokemonWithEvolutions

@Dao
interface PokemonDao {
    @Query("SELECT * FROM Pokemon")
    fun getAll(): Flow<List<Pokemon>>

    @Query("SELECT number, name, mainType, types FROM Pokemon")
    fun getAllSimple() : Flow<List<SimplePokemon>>

    @Query("SELECT * from Pokemon where number = :id")
    fun get(id: Int) : Pokemon

    @Transaction
    @Query("SELECT * from Pokemon where number = :id")
    fun getWithEvolution(id: Int) : PokemonWithEvolutions

    @Insert
    fun add(pokemon: Pokemon)
}