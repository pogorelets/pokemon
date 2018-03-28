package ru.helen.pokemon.di

import dagger.Subcomponent
import ru.helen.pokemon.pokemon.PokemonActivity

/**
 * Subcomponent PokemonComponent
 */
@PageScope
@Subcomponent(modules = arrayOf(PokemonModule::class))
interface PokemonComponent {
    fun inject(activity: PokemonActivity)
}