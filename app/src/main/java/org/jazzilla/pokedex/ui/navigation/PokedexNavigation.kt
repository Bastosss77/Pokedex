package org.jazzilla.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import org.jazzilla.pokedex.ui.screens.pokemonList.PokemonListScreen
import org.jazzilla.pokedex.ui.screens.pokemonDetail.PokemonDetailScreen

private val pokedexScreen = "pokedex"
private val pokemonScreen = "pokemon/{id}"

@Composable
fun PokedexNavigation() {
    val controller = rememberNavController()

    NavHost(navController = controller, startDestination = pokedexScreen) {
        composable(pokedexScreen) { PokemonListScreen(controller) }
        composable(pokemonScreen,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("id")?.let { pokemonId ->
                PokemonDetailScreen(controller, pokemonId)
            }
        }
    }
}