package ru.helen.pokemon.pagefragment

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.helen.pokemon.model.Pokemon
import ru.helen.pokemon.repository.NetworkRepository

/**
 * Interactor
 */
class InteractorImpl(val networkRepository: NetworkRepository) : Contract.Interactor {
    var listpokemons: MutableList<Pokemon> = ArrayList()

    override fun getPokemonList(offset: Int, limit: Int, listener: Contract.PokemonsLoaded) {
        Log.e("Interactor", "getPokemonList")
        networkRepository.getPokemonList(offset,limit).subscribe({ response -> getPokemons(response.result, listener) })
    }

    fun getPokemons(pokemons: List<Pokemon>, listener: Contract.PokemonsLoaded) {
        //Так как запросы довольно тяжёлые, запускаем асинхронно загрузку всех покемонов и призагружке каждого обновляем список
        //чтобы не выглядело, что ничего не происходит
        Log.e("getPokemons","getPokemons")
        Observable.just(pokemons)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable { pok -> pok }
                .flatMap { item -> Observable.just(item) }
                .subscribe({ pokemon ->
                    run {
                        networkRepository.getPokemon(pokemon.name)
                                .subscribe({ pokemon ->
                                    run {
                                        listpokemons.add(pokemon)
                                        Log.e("listpokemons",listpokemons.toString())
                                        listener.onSuccessPokemonLoaded(listpokemons)
                                    }
                                }, { throwable -> listener.onErrorPokemonLoaded(throwable.toString()) })
                    }
                })
    }
}