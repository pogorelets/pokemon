package ru.helen.pokemon.pagefragment

import android.util.Log
import ru.helen.pokemon.model.Pokemon

/**
 * Presenter
 */
class Presenter(val view: Contract.ViewPage, val interactor: Contract.Interactor) : Contract.PokemonsLoaded {
    //пока буду сохранять в статике страницы и список покемонов
    //позже можно попробовать viewState
    companion object {
        var offset = 0
        var limit = 12
        var first = 0


    }

    fun setPosition(firstposition : Int, visiblecount: Int){
        if (firstposition != first) {
            Log.e("PUFF", "PUFF")
            first = firstposition
            nextOffset()
            Log.e("NEWPOKEMONS","NEWPOKEMONS")
            getPokemons()
        }
    }

    fun nextOffset(){
        offset = offset + limit
    }

    fun getPokemons(){
        Log.e("LOADING","TRUE")
        view.showprogress()
        interactor.getPokemonList(limit,offset,this)

    }

    override fun onSuccessPokemonLoaded(pokemons: List<Pokemon>) {
        view.dismissprogress()
        view.updatelistpokemons(pokemons)
    }

    override fun onErrorPokemonLoaded(error: String) {
        view.dismissprogress()
        view.showerror(error)
    }
}