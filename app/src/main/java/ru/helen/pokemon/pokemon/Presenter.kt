package ru.helen.pokemon.pokemon

import ru.helen.pokemon.model.Pokemon

/**
 * Presenter
 */
class Presenter(val view: Contract.ViewPokemon, val interactor: Contract.Interactor) : Contract.Interactor.OnSuccessPokemonLoad {

    fun savePokemon(pokemon: Pokemon) {
        if (interactor.savePokemon(pokemon)){
            view.btnSaveInvisible()
        }else{
            view.btnSaveVisible()
        }
    }

    fun checkSave(id: Int) {
        if (interactor.checkPokemons(id)){
            view.initView(interactor.getLocalPokemon(id))
            view.btnSaveInvisible()
        } else {
            view.showprogress()
            interactor.getPokemon(id,this)

        }
    }

    fun deletePokemon(id:Int){
        if (interactor.deletePokemon(id)){
            view.back()
        }
    }


    override fun onSuccessLoad(pokemon: Pokemon) {
        view.initView(pokemon)
        view.btnSaveVisible()
        view.dismissprogress()
    }

    override fun onErrorLoad(error: String) {
        view.dismissprogress()
    }

}