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
            interactor.getPokemon(id,this)
            view.btnSaveVisible()
        }
    }

    fun deletePokemon(id:Int){
        if (interactor.deletePokemon(id)){
            view.back()
        }
    }

    fun getPokemon(id : Int){
        view.showprogress()
        interactor.getPokemon(id,this)
    }

//    fun getLocalPokemon(id : Int){
//        interactor.getPokemon(id,this)
//    }

    override fun onSuccessLoad(pokemon: Pokemon) {
        view.initView(pokemon)
        view.dismissprogress()
    }

    override fun onErrorLoad(error: String) {
        view.dismissprogress()
    }

}