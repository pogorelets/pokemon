package ru.helen.pokemon.pokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_pokemon.*
import kotlinx.android.synthetic.main.pokemon_item.view.*
import ru.helen.pokemon.App
import ru.helen.pokemon.R
import ru.helen.pokemon.model.Pokemon
import ru.helen.pokemon.repository.CurrentPokemon

class PokemonActivity : AppCompatActivity(), Contract.ViewPokemon {
    var adapter: AbilityAdapter = AbilityAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)
        App.instance.initPokemonComponent(this).inject(this)

        val pokemon = CurrentPokemon.pokemon
        Glide.with(this)
                .load(pokemon.sprites!!.frontDefault)
                .into(sprite)
        pokemonName.text = pokemon.name
        stat1.text = pokemon.stats!![0].stat.name
        stat2.text = pokemon.stats!![1].stat.name
        stat3.text = pokemon.stats!![2].stat.name
        stat4.text = pokemon.stats!![3].stat.name
        stat5.text = pokemon.stats!![4].stat.name
        value1.text = pokemon.stats!![0].baseStat.toString()
        value2.text = pokemon.stats!![1].baseStat.toString()
        value3.text = pokemon.stats!![2].baseStat.toString()
        value4.text = pokemon.stats!![3].baseStat.toString()
        value5.text = pokemon.stats!![4].baseStat.toString()
        rvability.layoutManager = LinearLayoutManager(this)
        rvability.setHasFixedSize(true)
        rvability.adapter = adapter
        adapter.changedata(pokemon.abilities!!)

    }



    override fun onStop() {
        super.onStop()
        App.instance.destroyPokemonComponent()
        //TODO отписать rx
    }
}
