package ru.helen.pokemon.repository

import io.reactivex.Single
import ru.helen.pokemon.model.Pokemon
import ru.helen.pokemon.model.Response
import ru.helen.pokemon.pagefragment.Contract

/**
 * Interface for NetworkRepository
 */
interface NetworkRepository {
    fun getPokemonList(offset: Int,limit: Int): Single<Response>

    fun getPokemon(name: String): Single<Pokemon>

    fun unsubscribe()
}