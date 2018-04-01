package ru.helen.pokemon.pokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_pokemon.*
import ru.helen.pokemon.App
import ru.helen.pokemon.R
import ru.helen.pokemon.repository.CurrentPokemon
import javax.inject.Inject

class PokemonActivity : AppCompatActivity(), Contract.ViewPokemon {
    @Inject
    lateinit var presenter: Presenter
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
        var adapterstats = StatsAdapter()
        rvstats.layoutManager = LinearLayoutManager(this)
        rvstats.setHasFixedSize(true)
        rvstats.adapter = adapterstats
        adapterstats.changedata(pokemon.stats!!)
        rvability.layoutManager = LinearLayoutManager(this)
        rvability.setHasFixedSize(true)
        rvability.adapter = adapter
        adapter.changedata(pokemon.abilities!!)
        btnSave.setOnClickListener {presenter.savePokemon(pokemon)}

    }



    override fun onStop() {
        super.onStop()
        App.instance.destroyPokemonComponent()
        //TODO отписать rx
    }
}
