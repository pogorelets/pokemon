package ru.helen.pokemon.repository

import ru.helen.pokemon.model.Pokemon
import ru.helen.pokemon.model.Pokemons

/**
 * Intrface for LocalRepository
 */
interface LocalBDRepository {
    fun getPokemon(id: Int): Pokemon

    fun getAllPokemons(): List<Pokemons>

    fun insertPokemon(pokemon: Pokemon): Boolean

    fun checkPokemons(id: Int): Boolean

    fun deletePokemons(id: Int): Boolean
}