package ru.helen.pokemon.di

import dagger.Module
import dagger.Provides
import ru.helen.pokemon.pokemon.Contract
import ru.helen.pokemon.pokemon.InteractorImpl
import ru.helen.pokemon.pokemon.Presenter
import ru.helen.pokemon.repository.LocalBDRepository
import ru.helen.pokemon.repository.NetworkRepository

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
    fun provideInteractor(localRepository: LocalBDRepository, networkRepository: NetworkRepository): Contract.Interactor = InteractorImpl(localRepository,networkRepository)


    @PokemonScope
    @Provides
    fun providePresenter(view: Contract.ViewPokemon, interactor: Contract.Interactor): Presenter = Presenter(view, interactor)

}