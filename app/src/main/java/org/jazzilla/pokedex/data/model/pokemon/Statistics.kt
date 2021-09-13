package org.jazzilla.pokedex.data.model.pokemon

data class Statistics(val hp: Stat,
                      val attack: Stat,
                      val defense: Stat,
                      val specialAttack: Stat,
                      val specialDefense: Stat,
                      val speed: Stat
) {
    val totalBase: Int
    get() = hp.base + attack.base + defense.base + specialAttack.base + specialDefense.base + speed.base
}

data class Stat(val base: Int, val min: Int, val max: Int)