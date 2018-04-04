package ru.helen.pokemon.pagefragment

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.helen.pokemon.model.Pokemon
import ru.helen.pokemon.repository.LocalBDRepository
import ru.helen.pokemon.repository.NetworkRepository
import javax.xml.datatype.DatatypeConstants.MINUTES
import javax.xml.datatype.DatatypeConstants.SECONDS
import android.R.attr.delay
import io.reactivex.schedulers.TestScheduler

import ru.helen.pokemon.model.Pokemons


/**
 * Interactor
 */
class InteractorImpl(val networkRepository: NetworkRepository, val localRepository: LocalBDRepository) : Contract.Interactor {


    companion object {
        var listpokemons: MutableList<Pokemons> = ArrayList()
    }


    override fun getPokemonList(limit: Int, offset: Int, listener: Contract.PokemonsLoaded) {
        networkRepository.getPokemonList(offset, limit)
                .subscribe({ r ->
                    run {
                        listpokemons.addAll(makeList(r.result))
                        listener.onSuccessPokemonLoaded(listpokemons)
                    }
                })

    }

    override fun getLocalPokemons(): List<Pokemon> {
        return localRepository.getAllPokemons()
    }

    override fun unsubscribe() {
        networkRepository.unsubscribe()
    }

    fun getID(url: String): String {
        val url = url.substringBeforeLast("/")
        return url.substringAfterLast("/")
    }

    fun makeList(pokemons: List<Pokemons>): List<Pokemons> {
        val list = pokemons
        for (pokemon in list) {
            val id = getID(pokemon.url)
            pokemon.id = id.toInt()
            pokemon.sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
        }
        return list
    }


}