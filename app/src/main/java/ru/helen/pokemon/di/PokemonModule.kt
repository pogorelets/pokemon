package ru.helen.pokemon.di

import dagger.Module
import dagger.Provides
import ru.helen.pokemon.pokemon.Contract
import ru.helen.pokemon.pokemon.InteractorImpl
import ru.helen.pokemon.pokemon.Presenter
import ru.helen.pokemon.repository.LocalBDRepository

/**
 * PokemonModule
 */
@Module
class PokemonModule(var view: Contract.ViewPokemon) {
    @PokemonScope
    @Provides
    fun provideView(): Contract.ViewPokemon = view

    @PokemonScope
    @Provides
    fun provideInteractor(localRepository: LocalBDRepository): Contract.Interactor = InteractorImpl(localRepository)


    @PokemonScope
    @Provides
    fun providePresenter(view: Contract.ViewPokemon, interactor: Contract.Interactor): Presenter = Presenter(view, interactor)

}