package ru.helen.pokemon.repository

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.helen.pokemon.model.Pokemon
import ru.helen.pokemon.model.Response


/**
 * API
 */
interface PokemonAPI {
    @GET("pokemon")
    fun getPokemonList(@Query("offset") offset: Int, @Query("limit") limit: Int): Single<Response>

    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") name: String): Single<Pokemon>
}