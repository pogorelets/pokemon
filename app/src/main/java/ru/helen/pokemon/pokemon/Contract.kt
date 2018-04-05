package ru.helen.pokemon.pokemon

import io.reactivex.Single
import ru.helen.pokemon.model.Pokemon

/**
 * Contract
 */
interface Contract {
    interface ViewPokemon{
        fun btnSaveVisible()
        fun btnSaveInvisible()
        fun back()
        fun initView(pokemon: Pokemon)
        fun showprogress()
        fun dismissprogress()
        fun showError(error: String)
    }

    interface Interactor{
        interface OnSuccessPokemonLoad{
            fun onSuccessLoad(pokemon: Pokemon)
            fun onErrorLoad(error: String)
        }
        fun savePokemon(pokemon: Pokemon):Boolean
        fun checkPokemons(id: Int): Boolean
        fun deletePokemon(id: Int): Boolean
        fun getPokemon(id: Int, listener: OnSuccessPokemonLoad)
        fun getLocalPokemon(id:Int): Pokemon
    }
}