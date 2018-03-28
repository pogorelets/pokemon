package ru.helen.pokemon.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.helen.pokemon.model.Pokemon
import ru.helen.pokemon.model.Response

/**
 * NetworkRepository
 */
class NetworkRepositoryImpl(var api: PokemonAPI) : NetworkRepository {
    lateinit var observerlist: Single<Response>
    lateinit var observerpokemon: Single<Pokemon>

    override fun getPokemonList(offset: Int, limit: Int): Single<Response> {
        observerlist = api.getPokemonList(offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        return observerlist
    }

    override fun getPokemon(name: String): Single<Pokemon> {
        observerpokemon = api.getPokemon(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        return observerpokemon
    }

    override fun unsubscribe() {
        observerlist.unsubscribeOn(Schedulers.io())
        observerpokemon.unsubscribeOn(Schedulers.io())
    }
}