package org.jazzilla.pokedex.ui.screens.pokemonDetail.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import org.jazzilla.pokedex.data.model.pokemon.PokemonWithEvolutions
import org.jazzilla.pokedex.data.repository.PokemonContract
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val pokemonRepository: PokemonContract) : ViewModel() {

    fun getOne(id: Int): Flow<PokemonWithEvolutions> = pokemonRepository.get(id)
}