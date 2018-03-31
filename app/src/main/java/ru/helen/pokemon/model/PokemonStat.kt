package ru.helen.pokemon.model

import com.google.gson.annotations.SerializedName

data class PokemonStat(
        val stat: NameResource,
        @SerializedName("base_stat")
        val baseStat: Int
)