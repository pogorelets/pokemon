package ru.helen.pokemon.main

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.helen.pokemon.repository.PokemonAPI
import java.util.concurrent.TimeUnit

/**
 * Временный класс для проверки апи -  к удалению
 */
class RestClient {
    private var instance: RestClient? = null
    private val BASE_URL = "https://pokeapi.co/api/v2/"
    val pokemonAPI: PokemonAPI
    val retrofit : Retrofit
    internal val okHttpClient = OkHttpClient.Builder()
            .readTimeout(600, TimeUnit.SECONDS)
            .connectTimeout(600, TimeUnit.SECONDS)
            .build()

    init {
        retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()

        pokemonAPI = retrofit.create(PokemonAPI::class.java)
    }

    fun getApi(): PokemonAPI = pokemonAPI

    fun getInstance(): RestClient {
        if (instance == null) {
            instance = RestClient()
        }
        return instance as RestClient
    }
}