package ru.helen.pokemon.repository

import ru.helen.pokemon.model.Pokemon
import ru.helen.pokemon.model.Pokemons
import ru.helen.pokemon.repository.localbd.DBHelper

/**
 * Local Repository
 */
class LocalBDRepositoryImpl(val dbhelper: DBHelper) : LocalBDRepository {
    override fun getPokemon(id: Int): Pokemon {
        return dbhelper.getPokemonsByID(id)
    }

    override fun deletePokemons(id: Int): Boolean {
        return dbhelper.deletePokemons(id)
    }

    override fun getAllPokemons(): List<Pokemons> {
        return dbhelper.getAllPokemons()
    }

    override fun insertPokemon(pokemon: Pokemon): Boolean {
        return dbhelper.insertPokemon(pokemon)
    }

    override fun checkPokemons(id: Int): Boolean {
        return dbhelper.checkPokemons(id)
    }
}