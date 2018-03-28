package ru.helen.pokemon.di

import dagger.Subcomponent
import ru.helen.pokemon.pagefragment.PageFragment


/**
 * Subcomponent PageComponent
 */
@PageScope
@Subcomponent(modules = arrayOf(PageModule::class))
interface PageComponent {
    fun inject(fragment: PageFragment)
}