package ru.helen.pokemon.pagefragment

import ru.helen.pokemon.model.Pokemon
import ru.helen.pokemon.model.Pokemons

/**
 * Contracr for PageFragment
 */
interface Contract {

    interface ViewPage{
        fun showprogress()
        fun dismissprogress()
        fun showerror(error : String)
        fun updatelistpokemons(pokemons : List<Pokemons>)
        fun updatelocalpokemons(pokemons : List<Pokemon>)
        fun onPokemonClick(pokemon: Pokemons)
    }
    interface Interactor{
        fun getPokemonList(limit: Int, offset: Int, listener: Contract.PokemonsLoaded)
        fun getLocalPokemons(): List<Pokemon>
        fun unsubscribe()
    }

    interface PokemonsLoaded {
        fun onSuccessPokemonLoaded(pokemons : List<Pokemons>)
        fun onErrorPokemonLoaded(error : String)
    }
}