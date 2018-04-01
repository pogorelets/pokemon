package ru.helen.pokemon.pagefragment

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.helen.pokemon.model.Pokemon
import ru.helen.pokemon.repository.LocalBDRepository
import ru.helen.pokemon.repository.NetworkRepository

/**
 * Interactor
 */
class InteractorImpl(val networkRepository: NetworkRepository, val localRepository: LocalBDRepository) : Contract.Interactor {


    var listpokemons: MutableList<Pokemon> = ArrayList()

    override fun getPokemonList(limit: Int, offset: Int, listener: Contract.PokemonsLoaded) {
        networkRepository.getPokemonList(offset, limit).subscribe({ response -> getPokemons(response.count, response.result, listener) }, { throwable -> listener.onErrorPokemonLoaded(throwable.toString()) })
    }

    fun getPokemons(allpokemons: Int, pokemons: List<Pokemon>, listener: Contract.PokemonsLoaded) {
        //Пока список покемонов не загружен полностью
        if (listpokemons.size <= allpokemons) {
            var count: Int = pokemons.size
            var cnt = 0
            Observable.just(pokemons)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMapIterable { pok -> pok }
                    .flatMap { item -> Observable.just(item) }
                    .subscribe({ pokemon ->
                        run {
                            Log.e("pokemon", pokemon.name)
                            networkRepository.getPokemon(pokemon.name)
                                    .subscribe({ pokemon ->
                                        run {
                                            listpokemons.add(pokemon)
                                            cnt = cnt + 1
                                            if (cnt == count) {
                                                listener.onSuccessPokemonLoaded(listpokemons)
                                            }
                                        }
                                    }, { throwable ->
                                        run {
                                            listener.onErrorPokemonLoaded(throwable.toString())
                                            //залепушно, надо додумать
                                            cnt = cnt + 1
                                            if (cnt == count) {
                                                listener.onSuccessPokemonLoaded(listpokemons)
                                            }
                                        }
                                    })
                        }
                    })
        }
    }

    override fun getLocalPokemons(): List<Pokemon> {
        return localRepository.getAllPokemons()
    }

}