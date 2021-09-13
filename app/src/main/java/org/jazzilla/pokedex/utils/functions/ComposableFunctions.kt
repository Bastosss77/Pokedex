package org.jazzilla.pokedex.utils.functions

import androidx.annotation.PluralsRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun pluralsResource(@PluralsRes id: Int, quantity: Int, vararg arguments: Any? = emptyArray()) : String =
    LocalContext.current.resources.getQuantityString(id, quantity, *arguments)