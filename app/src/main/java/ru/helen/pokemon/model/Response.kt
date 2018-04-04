package ru.helen.pokemon.model

import com.google.gson.annotations.SerializedName

/**
 * Response
 */
data class Response(
        @SerializedName("count")
        var count: Int,
        @SerializedName("results")
        var result: List<Pokemons>
)
