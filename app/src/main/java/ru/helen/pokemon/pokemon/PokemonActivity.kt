package ru.helen.pokemon.pokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ru.helen.pokemon.App
import ru.helen.pokemon.R

class PokemonActivity : AppCompatActivity(), Contract.ViewPokemon {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)
        App.instance.initPokemonComponent(this).inject(this)
    }



    override fun onStop() {
        super.onStop()
        App.instance.destroyPokemonComponent()
        //TODO отписать rx
    }
}
