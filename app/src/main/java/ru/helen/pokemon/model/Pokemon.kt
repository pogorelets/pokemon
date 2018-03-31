package ru.helen.pokemon.model

import com.google.gson.annotations.SerializedName

data class Pokemon (
        var id: Int?,
        @SerializedName("name")
        var name: String,
        val abilities: List<PokemonAbility>?,
        val stats: List<PokemonStat>?,
        val sprites: PokemonSprites?
)