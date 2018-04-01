package ru.helen.pokemon.pokemon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_pokemon.*
import ru.helen.pokemon.App
import ru.helen.pokemon.R
import ru.helen.pokemon.main.MainActivity
import ru.helen.pokemon.pagefragment.PageFragment
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
        presenter.checkSave(pokemon.id!!)
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
        btnDelete.setOnClickListener { presenter.deletePokemon(pokemon.id!!) }

    }

    override fun btnSaveVisible() {
        btnSave.visibility = View.VISIBLE
        btnDelete.visibility = View.GONE
    }

    override fun btnSaveInvisible() {
        btnSave.visibility = View.GONE
        btnDelete.visibility = View.VISIBLE
    }

    override fun back() {
        super.onBackPressed()
    }

    override fun onStop() {
        super.onStop()
        App.instance.destroyPokemonComponent()

    }
}
