package ru.helen.pokemon.model

import com.google.gson.annotations.SerializedName


data class PokemonSprites(
        @SerializedName("back_default")
        val backDefault: String?,
        @SerializedName("back_shiny")
        val backShiny: String?,
        @SerializedName("front_default")
        var frontDefault: String?,
        @SerializedName("front_shiny")
        val frontShiny: String?,
        @SerializedName("back_female")
        val backFemale: String?,
        @SerializedName("back_shiny_female")
        val backShinyFemale: String?,
        @SerializedName("front_female")
        val frontFemale: String?,
        @SerializedName("front_shiny_female")
        val frontShinyFemale: String?
)
