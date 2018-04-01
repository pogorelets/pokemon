package ru.helen.pokemon.repository.localbd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by lenap on 01.04.2018.
 */
class DBHelper(context: Context) :  SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(SQL_CREATE_POKEMONS)
        db.execSQL(SQL_CREATE_STATS)
        db.execSQL(SQL_CREATE_ABILITY)
        db.execSQL(SQL_CREATE_SPRITES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        val DATABASE_NAME = "Pokemon.db"
        val DATABASE_VERSION =1

        private val SQL_CREATE_POKEMONS =
                "CREATE TABLE " + PokemonContract.Pokemon.TABLE_NAME + " (" +
                        PokemonContract.Pokemon.ID  + " INTEGER PRIMARY KEY," +
                        PokemonContract.Pokemon.NAME  + " TEXT)"

        private val SQL_DELETE_POKEMONS = "DROP TABLE IF EXISTS " +  PokemonContract.Pokemon.TABLE_NAME

        private val SQL_CREATE_STATS =
                "CREATE TABLE " + PokemonContract.Stat.TABLE_NAME + " (" +
                        PokemonContract.Stat.ID_POKEMON  + " INTEGER, " +
                        PokemonContract.Stat.NAME  + " TEXT, " +
                        PokemonContract.Stat.URL + " TEXT, " +
                        PokemonContract.Stat.BASESTAT + " INTEGER, " +
                        "PRIMARY KEY(" + PokemonContract.Stat.ID_POKEMON+ "," +
                        PokemonContract.Stat.NAME + ")," +
                        "FOREIGN KEY (" + PokemonContract.Stat.ID_POKEMON + ") REFERENCES " +  PokemonContract.Pokemon.TABLE_NAME + "(" + PokemonContract.Pokemon.ID +
                        ") ON DELETE CASCADE ON UPDATE NO ACTION )"

        private val SQL_DELETE_STATS = "DROP TABLE IF EXISTS " +  PokemonContract.Stat.TABLE_NAME

        private val SQL_CREATE_ABILITY =
                "CREATE TABLE " + PokemonContract.Ability.TABLE_NAME + " (" +
                        PokemonContract.Ability.ID_POKEMON  + " INTEGER, " +
                        PokemonContract.Ability.NAME  + " TEXT, " +
                        PokemonContract.Ability.URL + " TEXT, " +
                        "PRIMARY KEY(" + PokemonContract.Ability.ID_POKEMON+ "," +
                        PokemonContract.Ability.NAME + ")," +
                        "FOREIGN KEY (" + PokemonContract.Ability.ID_POKEMON + ") REFERENCES " +  PokemonContract.Pokemon.TABLE_NAME + "(" + PokemonContract.Pokemon.ID +
                        ") ON DELETE CASCADE ON UPDATE NO ACTION )"

        private val SQL_DELETE_ABILITY = "DROP TABLE IF EXISTS " +  PokemonContract.Ability.TABLE_NAME

        private val SQL_CREATE_SPRITES =
                "CREATE TABLE " + PokemonContract.Sprites.TABLE_NAME + " (" +
                        PokemonContract.Sprites.ID_POKEMON  + " INTEGER, " +
                        PokemonContract.Sprites.NAME  + " TEXT, " +
                        PokemonContract.Sprites.VALUE + " TEXT, " +
                        "PRIMARY KEY(" + PokemonContract.Sprites.ID_POKEMON+ "," +
                        PokemonContract.Sprites.NAME + ")," +
                        "FOREIGN KEY (" + PokemonContract.Sprites.ID_POKEMON + ") REFERENCES " +  PokemonContract.Pokemon.TABLE_NAME + "(" + PokemonContract.Pokemon.ID +
                        ") ON DELETE CASCADE ON UPDATE NO ACTION )"

        private val SQL_DELETE_SPRITES = "DROP TABLE IF EXISTS " +  PokemonContract.Sprites.TABLE_NAME



    }
}