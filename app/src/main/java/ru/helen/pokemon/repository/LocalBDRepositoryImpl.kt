package ru.helen.pokemon.repository

import ru.helen.pokemon.model.Pokemon
import ru.helen.pokemon.repository.localbd.DBHelper

/**
 * Local Repository
 */
class LocalBDRepositoryImpl(val dbhelper: DBHelper) : LocalBDRepository {
    override fun getAllPokemons(): List<Pokemon> {
        return dbhelper.getAllPokemons()
    }

    override fun insertPokemon(pokemon: Pokemon): Boolean {
        return dbhelper.insertPokemon(pokemon)
    }

    override fun checkPokemons(id: Int): Boolean {
        return dbhelper.checkPokemons(id)
    }
}