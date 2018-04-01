package ru.helen.pokemon.repository

import ru.helen.pokemon.model.Pokemon

/**
 * Intrface for LocalRepository
 */
interface LocalBDRepository {
    fun getAllPokemons(): List<Pokemon>

    fun insertPokemon(pokemon: Pokemon): Boolean

    fun checkPokemons(id: Int): Boolean
}