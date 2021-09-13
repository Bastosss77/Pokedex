package org.jazzilla.pokedex.data.model.pokemon

data class Breeding(val eggTypes: List<EggType>,
                    val cyclesToHatch: Int, //1 cycle is 255 steps
                    //val stepsToHatch: Int
) {
    val stepsToHatch: Int get() = cyclesToHatch * 255
}

enum class EggType {
    AMORPHOUS,
    BUG,
    DRAGON,
    FAIRY,
    FIELD,
    FLYING,
    PLANT,
    HUMAN_LIKE,
    MINERAL,
    MONSTER,
    WATER_1,
    WATER_2,
    WATER_3,
    DITTO,
    UNKNOWN
}