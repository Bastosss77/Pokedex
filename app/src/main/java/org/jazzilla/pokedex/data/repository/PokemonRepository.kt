package org.jazzilla.pokedex.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jazzilla.pokedex.data.dao.PokemonDao
import org.jazzilla.pokedex.data.model.SimplePokemon
import org.jazzilla.pokedex.data.model.pokemon.EvolutionChain
import org.jazzilla.pokedex.data.model.pokemon.Pokemon
import org.jazzilla.pokedex.data.model.pokemon.PokemonWithEvolutions
import org.jazzilla.pokedex.data.model.pokemon.fakeSalameche
import javax.inject.Inject

interface PokemonContract {
    fun insertPokemon(pokemon: Pokemon)
    fun getAllSimple() : Flow<List<SimplePokemon>>
    fun get(id: Int) : Flow<PokemonWithEvolutions>
}

class PokemonRepository @Inject constructor(private val dao: PokemonDao) : PokemonContract {

    override fun insertPokemon(pokemon: Pokemon) {
        dao.add(pokemon)
    }

    override fun getAllSimple(): Flow<List<SimplePokemon>> = dao.getAllSimple()

    override fun get(id: Int) : Flow<PokemonWithEvolutions> = flow {
        emit(dao.getWithEvolution(id))
    }.flowOn(Dispatchers.IO)
}