package ru.helen.pokemon.pokemon

import ru.helen.pokemon.model.Pokemon
import ru.helen.pokemon.repository.LocalBDRepository
import ru.helen.pokemon.repository.LocalBDRepositoryImpl
import ru.helen.pokemon.repository.localbd.DBHelper

/**
 * Interactor
 */
class InteractorImpl(val localRepository: LocalBDRepository) : Contract.Interactor{
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