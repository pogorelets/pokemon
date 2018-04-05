package ru.helen.pokemon.pagefragment

import ru.helen.pokemon.model.Pokemons

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
            first = firstposition
            nextOffset()
            getPokemons()
        }
    }

    fun nextOffset(){
        offset = offset + limit
    }

    fun getPokemons(){
        view.showprogress()
        interactor.getPokemonList(limit,offset,this)

    }

    fun getLocalPokemons(){
        view.updatelocalpokemons(interactor.getLocalPokemons())
    }

    fun unsubscribe(){
        interactor.unsubscribe()
    }

    override fun onSuccessPokemonLoaded(pokemons: List<Pokemons>) {
        view.dismissprogress()
        view.updatelistpokemons(pokemons)
    }

    override fun onErrorPokemonLoaded(error: String) {
        view.dismissprogress()
        view.showerror(error)
    }
}