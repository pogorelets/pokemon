package ru.helen.pokemon.pagefragment

import ru.helen.pokemon.model.Pokemon

/**
 * Contracr for PageFragment
 */
interface Contract {

    interface ViewPage{
        fun showprogress()
        fun dismissprogress()
        fun showerror(error : String)
        fun updatelistpokemons(pokemons : List<Pokemon>)
        fun onPokemonClick(pokemon: Pokemon)
    }
    interface Interactor{
        fun getPokemonList(limit: Int, offset: Int, listener: Contract.PokemonsLoaded)
    }

    interface PokemonsLoaded {
        fun onSuccessPokemonLoaded(pokemons : List<Pokemon>)
        fun onErrorPokemonLoaded(error : String)
    }
}