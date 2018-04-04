package ru.helen.pokemon.model

data class Pokemons(
        val url: String,
        val name: String,
        var id: Int?,
        var sprite: String?
)