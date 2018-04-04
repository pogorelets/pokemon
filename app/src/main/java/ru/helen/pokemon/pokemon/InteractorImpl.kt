package ru.helen.pokemon.pokemon


import ru.helen.pokemon.model.Pokemon
import ru.helen.pokemon.repository.LocalBDRepository
import ru.helen.pokemon.repository.NetworkRepository


/**
 * Interactor
 */
class InteractorImpl(val localRepository: LocalBDRepository, val networkRepository: NetworkRepository) : Contract.Interactor{
    override fun getPokemon(id: Int, listener: Contract.Interactor.OnSuccessPokemonLoad) {
        networkRepository.getPokemon(id).subscribe({r -> listener.onSuccessLoad(r)},{throwable->listener.onErrorLoad(throwable.toString())})
    }

    override fun deletePokemon(id: Int): Boolean {
        return localRepository.deletePokemons(id)
    }

    override fun checkPokemons(id: Int): Boolean {
        return localRepository.checkPokemons(id)
    }

    override fun savePokemon(pokemon: Pokemon): Boolean {
        return localRepository.insertPokemon(pokemon)
    }
}