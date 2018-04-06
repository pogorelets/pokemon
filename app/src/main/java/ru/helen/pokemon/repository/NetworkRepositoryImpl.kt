package ru.helen.pokemon.repository

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.helen.pokemon.model.Pokemon
import ru.helen.pokemon.model.Response
import ru.helen.pokemon.pagefragment.Contract

/**
 * NetworkRepository
 */
class NetworkRepositoryImpl(var api: PokemonAPI) : NetworkRepository {
    lateinit var observerlist: Single<Response>
    lateinit var observerpokemon: Single<Pokemon>


    override fun getPokemonList(limit: Int,offset: Int): Single<Response> {
        return api.getPokemonList(offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


    }

    override fun getPokemon(id: Int): Single<Pokemon> {
        observerpokemon = api.getPokemon(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        return observerpokemon
    }

    override fun unsubscribe() {

    }



}