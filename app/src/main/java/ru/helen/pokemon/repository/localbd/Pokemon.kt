package ru.helen.pokemon.repository.localbd

import android.provider.BaseColumns

/**
 * Created by lenap on 01.04.2018.
 */
object PokemonContract {

    class Pokemon : BaseColumns {
        companion object {
            val TABLE_NAME: String = "pokemon"
            val ID: String = "id_pokemon"
            val NAME: String = "name_pokemon"
        }
    }

    abstract class Stat : BaseColumns {
        companion object {
            val TABLE_NAME: String = "stats"
            val NAME: String = "name_stat"
            val URL: String = "url"
            val BASESTAT: String = "base_stat"
            val ID_POKEMON: String = "id_pokemon"
        }
    }

    abstract class Ability : BaseColumns {
        companion object {
            val TABLE_NAME = "ability"
            val NAME: String = "name_ability"
            val URL: String = "url"
            val ID_POKEMON: String = "id_pokemon"
        }

    }

    abstract class Sprites : BaseColumns {
        companion object {
            val TABLE_NAME = "sprites"
            val NAME: String = "name_sprite"
            val VALUE: String = "value"
            val ID_POKEMON = "id_pokemon"
        }

    }
}