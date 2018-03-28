package ru.helen.pokemon.pagefragment

import ru.helen.pokemon.model.Pokemon

/**
 * Contracr for PageFragment
 */
interface Contract {
    interface ViewPage{
        fun onPokemonClick(pokemon: Pokemon)
    }
    interface Interactor{

    }
}