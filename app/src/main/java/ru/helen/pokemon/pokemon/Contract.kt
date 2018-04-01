package ru.helen.pokemon.pokemon

import ru.helen.pokemon.model.Pokemon

/**
 * Contract
 */
interface Contract {
    interface ViewPokemon{
        fun btnSaveVisible()
        fun btnSaveInvisible()

    }

    interface Interactor{
        fun savePokemon(pokemon: Pokemon):Boolean
        fun checkPokemons(id: Int): Boolean
    }
}