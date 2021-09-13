package org.jazzilla.pokedex.ui.screens.pokemonList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.jazzilla.pokedex.data.model.SimplePokemon
import org.jazzilla.pokedex.data.model.pokemon.Pokemon
import org.jazzilla.pokedex.data.model.pokemon.PokemonWithEvolutions
import org.jazzilla.pokedex.data.repository.PokemonContract
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(private val pokemonRepository: PokemonContract) : ViewModel() {

    fun getAllSimplified() : Flow<List<SimplePokemon>> = pokemonRepository.getAllSimple()
}