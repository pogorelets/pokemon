package ru.helen.pokemon.di

import dagger.Component
import javax.inject.Singleton

/**
 * AppComponent
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun getPageComponent(pageModule:PageModule):PageComponent
    fun getPokemonComponent(pokemonModule: PokemonModule):PokemonComponent
}