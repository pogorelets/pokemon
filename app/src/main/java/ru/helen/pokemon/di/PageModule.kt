package ru.helen.pokemon.di

import dagger.Module
import ru.helen.pokemon.pagefragment.Contract
import dagger.Provides
import ru.helen.pokemon.pagefragment.InteractorImpl
import ru.helen.pokemon.pagefragment.Presenter
import ru.helen.pokemon.repository.LocalBDRepository
import ru.helen.pokemon.repository.NetworkRepository

/**
 * PageModule
 */

@Module
class PageModule(var view: Contract.ViewPage) {
    @PageScope
    @Provides
    fun provideView(): Contract.ViewPage = view

    @PageScope
    @Provides
    fun provideInteractor(networkRepository: NetworkRepository, localRepository: LocalBDRepository): Contract.Interactor = InteractorImpl(networkRepository,localRepository)

    @PageScope
    @Provides
    fun providePresenter(view: Contract.ViewPage, interactor: Contract.Interactor): Presenter = Presenter(view, interactor)

}