package ru.helen.pokemon.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.helen.pokemon.model.Pokemon
import ru.helen.pokemon.model.Response
import ru.helen.pokemon.pagefragment.Contract

/**
 * Interface for NetworkRepository
 */
interface NetworkRepository {
    fun getPokemonList(limit: Int, offset: Int): Single<Response>

    fun getPokemon(name: String): Observable<Pokemon>

    fun unsubscribe()
}