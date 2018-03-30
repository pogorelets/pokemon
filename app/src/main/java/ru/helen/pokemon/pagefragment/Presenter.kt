package ru.helen.pokemon.pagefragment

import android.util.Log
import ru.helen.pokemon.model.Pokemon

/**
 * Presenter
 */
class Presenter(val view: Contract.ViewPage, val interactor: Contract.Interactor) : Contract.PokemonsLoaded {
    fun getPokemons(offset : Int, limit : Int){
        Log.e("Presenter", "getPokemons")
        interactor.getPokemonList(offset,limit,this)
    }

    override fun onSuccessPokemonLoaded(pokemons: List<Pokemon>) {
        view.updatelistpokemons(pokemons)
    }

    override fun onErrorPokemonLoaded(error: String) {
        view.showerror(error)
    }
}