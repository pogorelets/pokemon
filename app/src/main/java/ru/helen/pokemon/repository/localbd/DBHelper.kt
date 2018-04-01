package ru.helen.pokemon.repository.localbd

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ru.helen.pokemon.model.*

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

    }

    @Throws(SQLiteConstraintException::class)
    fun insertPokemon(pokemon: Pokemon): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        var values = ContentValues()
        values.put(PokemonContract.Pokemon.ID, pokemon.id)
        values.put(PokemonContract.Pokemon.NAME, pokemon.name)

        // Insert the new row, returning the primary key value of the new row

        db.insert(PokemonContract.Pokemon.TABLE_NAME, "", values)



        for (stat in pokemon.stats!!){
            values = ContentValues()
            values.put(PokemonContract.Stat.ID_POKEMON, pokemon.id)
            values.put(PokemonContract.Stat.NAME, stat.stat.name)
            values.put(PokemonContract.Stat.URL, stat.stat.url)
            values.put(PokemonContract.Stat.BASESTAT, stat.baseStat)
            db.insert(PokemonContract.Stat.TABLE_NAME, "", values)
        }

        for (ability in pokemon.abilities!!){
            values = ContentValues()
            values.put(PokemonContract.Ability.NAME,ability.ability.name)
            values.put(PokemonContract.Ability.URL,ability.ability.url)
            values.put(PokemonContract.Ability.ID_POKEMON, pokemon.id)
            db.insert(PokemonContract.Ability.TABLE_NAME, "", values)
        }


        //Нам нужна только одна картинка, поэтому вставим её
        values = ContentValues()
        values.put(PokemonContract.Sprites.NAME, "frontDefault")
        values.put(PokemonContract.Sprites.ID_POKEMON, pokemon.id)
        values.put(PokemonContract.Sprites.VALUE, pokemon.sprites!!.frontDefault)

        db.insert(PokemonContract.Sprites.TABLE_NAME,"",values)
        db.close()
        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun checkPokemons(id: Int): Boolean {

        val db = readableDatabase
        val query = "SELECT * FROM ${PokemonContract.Pokemon.TABLE_NAME} WHERE ${PokemonContract.Pokemon.ID} =\"$id\""
        val cursor = db.rawQuery(query, null)
        if (cursor != null) return true
        return false
    }

    @Throws(SQLiteConstraintException::class)
    fun getAllPokemons(): List<Pokemon> {
        val db = readableDatabase
        val query = "SELECT * FROM ${PokemonContract.Pokemon.TABLE_NAME} ORDER BY ${PokemonContract.Pokemon.ID} DESC"
        val cursor = db.rawQuery(query, null)
        var pokemons: MutableList<Pokemon> = ArrayList()

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                val id = cursor.getInt(cursor.getColumnIndex(PokemonContract.Pokemon.ID))
                val name = cursor.getString(cursor.getColumnIndex(PokemonContract.Pokemon.NAME))
                val stats = getStatForPokemons(id, db)
                val abilities = getAbilityForPokemons(id, db)
                val sprite = getSpritesForPokemons(id, db)
                pokemons.add(Pokemon(id,name,abilities,stats,sprite))
                cursor.moveToNext()
            }
        }
        db.close()
        return pokemons

    }

    @Throws(SQLiteConstraintException::class)
    fun getStatForPokemons(id: Int, db: SQLiteDatabase): List<PokemonStat> {
        val query = "SELECT * FROM ${PokemonContract.Stat.TABLE_NAME} WHERE ${PokemonContract.Stat.ID_POKEMON} =  \"$id\""
        val cursor = db.rawQuery(query, null)
        var stats: MutableList<PokemonStat> = ArrayList()

        var url: String
        var name: String
        var basestat: Int
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                basestat = cursor.getInt(cursor.getColumnIndex(PokemonContract.Stat.BASESTAT))
                name = cursor.getString(cursor.getColumnIndex(PokemonContract.Stat.NAME))
                url = cursor.getString(cursor.getColumnIndex(PokemonContract.Stat.URL))
                stats.add(PokemonStat(NameResource(url,name), basestat))
                cursor.moveToNext()
            }
        }
        return stats

    }

    @Throws(SQLiteConstraintException::class)
    fun getAbilityForPokemons(id: Int, db: SQLiteDatabase ): List<PokemonAbility> {
        val query = "SELECT * FROM ${PokemonContract.Ability.TABLE_NAME} WHERE ${PokemonContract.Ability.ID_POKEMON} =  \"$id\""
        val cursor = db.rawQuery(query, null)
        var abilities: MutableList<PokemonAbility> = ArrayList()

        var url: String
        var name: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                name = cursor.getString(cursor.getColumnIndex(PokemonContract.Ability.NAME))
                url = cursor.getString(cursor.getColumnIndex(PokemonContract.Ability.URL))
                abilities.add(PokemonAbility(NameResource(url,name)))
                cursor.moveToNext()
            }
        }
        return abilities
    }

    @Throws(SQLiteConstraintException::class)
    fun getSpritesForPokemons(id: Int, db: SQLiteDatabase): PokemonSprites {
        val query = "SELECT * FROM ${PokemonContract.Sprites.TABLE_NAME} WHERE ${PokemonContract.Sprites.ID_POKEMON} =  \"$id\" "
        val cursor = db.rawQuery(query, null)
        var spite: PokemonSprites = PokemonSprites(null,null,null,null,null,null,null,null)

        var url: String
        if (cursor!!.moveToFirst()) {
            cursor.moveToFirst()
            url = cursor.getString(cursor.getColumnIndex(PokemonContract.Sprites.VALUE))
            spite.frontDefault = url
            cursor.moveToNext()
        }
        return spite
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