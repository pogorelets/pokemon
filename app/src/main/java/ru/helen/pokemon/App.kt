package ru.helen.pokemon

import android.app.Application
import okhttp3.internal.Internal.instance
import ru.helen.pokemon.di.*
import ru.helen.pokemon.pagefragment.PageFragment
import ru.helen.pokemon.pokemon.PokemonActivity


/**
 * Application
 */
class App: Application() {
    private lateinit var appComponent: AppComponent
    private var pageComponent: PageComponent? = null
    private var pokemonComponent: PokemonComponent? = null

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(applicationContext)).build()
    }

    fun initPageComponent(fragment: PageFragment): PageComponent {
        pageComponent = appComponent.getPageComponent(PageModule(fragment))
        return pageComponent as PageComponent
    }

    fun initPokemonComponent(activity: PokemonActivity): PokemonComponent {
        pokemonComponent = appComponent.getPokemonComponent(PokemonModule(activity))
        return pokemonComponent as PokemonComponent
    }

    fun destroyPageComponent() {
        pageComponent = null
    }

    fun destroyPokemonComponent() {
        pokemonComponent = null
    }
}