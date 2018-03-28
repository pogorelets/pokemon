package ru.helen.pokemon.model

/**
 * Created by lenap on 27.03.2018.
 */
data class Pokemon(
        var id: Int?,
        var name: String,
        val abilities: List<PokemonAbility>?,
        val stats: List<PokemonStat>?,
        val sprites: PokemonSprites?

)