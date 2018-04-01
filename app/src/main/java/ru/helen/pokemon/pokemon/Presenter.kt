package ru.helen.pokemon.pokemon

import ru.helen.pokemon.model.Pokemon

/**
 * Presenter
 */
class Presenter(val view: Contract.ViewPokemon, val interactor: Contract.Interactor) {

    fun savePokemon(pokemon: Pokemon) {
        interactor.savePokemon(pokemon)
    }

    fun checkSave(id: Int) {
        if (interactor.checkPokemons(id)) {
            view.btnSaveInvisible()
        } else {
            view.btnSaveVisible()
        }
    }

}