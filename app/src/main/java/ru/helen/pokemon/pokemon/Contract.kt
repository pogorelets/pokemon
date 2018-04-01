package ru.helen.pokemon.pokemon

import ru.helen.pokemon.model.Pokemon

/**
 * Contract
 */
interface Contract {
    interface ViewPokemon{

    }

    interface Interactor{
        fun savePokemon(pokemon: Pokemon):Boolean
    }
}