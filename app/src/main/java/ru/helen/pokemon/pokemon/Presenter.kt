package ru.helen.pokemon.pokemon

import ru.helen.pokemon.model.Pokemon

/**
 * Presenter
 */
class Presenter(val view: Contract.ViewPokemon, val interactor: Contract.Interactor) {

    fun savePokemon(pokemon: Pokemon) {
        if (interactor.savePokemon(pokemon)){
            view.btnSaveInvisible()
        }else{
            view.btnSaveVisible()
        }
    }

    fun checkSave(id: Int) {
        if (interactor.checkPokemons(id)) {
            view.btnSaveInvisible()
        } else {
            view.btnSaveVisible()
        }
    }

    fun deletePokemon(id:Int){
        if (interactor.deletePokemon(id)){
            view.back()
        }
    }

}